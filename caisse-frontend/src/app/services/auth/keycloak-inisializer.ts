import { KeycloakService } from 'keycloak-angular';
export function initializer(keycloak: KeycloakService) {

  return () => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
         config: {
            url: "http://localhost:8080/auth",
            realm: "pfe",
            clientId: "pfe",

          },
          loadUserProfileAtStartUp: false,
          enableBearerInterceptor: true,
          bearerExcludedUrls: ['/account/login'],
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false,
          },
          bearerPrefix: 'Bearer'

        }).then(
          authenticated => {
            const keycloakAuth = keycloak.getKeycloakInstance();
            keycloakAuth.onAuthRefreshError=() =>{
            keycloak.logout()
            }
            resolve(authenticated);
          }).catch(
            error => {
              console.log(error);
              reject(error);
            }
          );
      } catch (error) {
        reject(error);
      }
    })
  }
}
