package com.caisse.config;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;

@Configuration
@ComponentScan(
        basePackageClasses = KeycloakSecurityComponents.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "org.keycloak.adapters.springsecurity.management.HttpSessionManager"))
@EnableWebSecurity
public class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(KeycloakConfig.class);

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() { //replace HttpSessionStrategy
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    //Registers the KeycloakAuthenticationProvider with the authentication manager.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){

        try {
            SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
            grantedAuthorityMapper.setPrefix("ROLE_");
            grantedAuthorityMapper.setConvertToUpperCase(true);

            KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
            keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);

            auth.authenticationProvider(keycloakAuthenticationProvider());

        } catch (Exception ex) {
            logger.error("SecurityConfig.configureGlobal: " + ex);

        }
    }

    //Load Keycloak properties from service config-file
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    //Defines the session authentication strategy.
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                //BEGIN
                //USER -done to be tested

                .antMatchers(HttpMethod.GET, "/test**").access("hasAuthority('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.GET, "/").access("hasAnyAuthority('ADMIN','MANAGER','EXPERT','STANDARD')")


                .anyRequest().authenticated()

                .and()
                .cors()
                .and()
                .csrf().disable()
                //BEGIN Login/Logout
                .formLogin()
                .permitAll()//.successHandler(authenticationSuccessHandler) //
                .and()
                .logout()//.clearAuthentication(true) //Add .clearAuthentication(true) to logout()
                .addLogoutHandler(keycloakLogoutHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll();
        //END Login/Logout

        //BEGIN Session
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false) // if true generate an error when user login after reaching maximumSession (SessionAuthenticationStrategy rejected the authentication object / SessionAuthenticationException: Maximum sessions of 1 for this principal exceeded)
                .sessionRegistry(sessionRegistry());


    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccessToken accessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return ((KeycloakSecurityContext) ((KeycloakAuthenticationToken) request.getUserPrincipal()).getCredentials()).getToken();
    }

    ///BEGIN session
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthStr() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
}