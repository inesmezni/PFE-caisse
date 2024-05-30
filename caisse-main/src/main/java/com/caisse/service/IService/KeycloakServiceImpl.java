package com.caisse.service.IService;


import com.caisse.config.KeycloakAdminClientConfig;
import com.caisse.dto.KeycloakProfileDto;
import com.caisse.exception.ApiRequestException;
import com.caisse.service.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.auth-server-url}")
    public String serverUrl;

    @Value("${keycloak.realm}")
    public String prixpresRealm;

    @Value("${username}")
    String keycloakAdmin;

    @Value("${password}")
    String keycloakAdminPassword;

    @Value("${not.allowed.groups}")
    String notAllowedGroups;

    MediaType JSON2 = MediaType.parse("application/json; charset=utf-8");
    private final static String USERS = "/users/";
    private final static String AUTHORIZATION = "Authorization";
    private final static String BEARER = "Bearer ";


    @Override
    public String getUserInfo(String idKeyCloack){
        OkHttpClient getUserInfoHttp = new OkHttpClient();
        try {
            Request requestDelete = new Request.Builder()
                    .url(serverUrl + "/admin/realms/" + prixpresRealm + USERS + idKeyCloack)
                    .addHeader(AUTHORIZATION, BEARER + getToken())
                    .get()
                    .build();
            Response response = getUserInfoHttp.newCall(requestDelete).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCurrentUserDetails() {
        KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal = (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return principal.getKeycloakSecurityContext().getToken().getSubject();
    }

    private Keycloak getKeycloakClientMaster(KeycloakAdminClientConfig config) {
        return KeycloakBuilder.builder()
                .serverUrl(config.getServerUrl())
                .realm(config.getRealm())
                .clientId(config.getClientId())
                .authorization(getToken())
                .build();
    }

    public String getToken() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType("password")
                .realm("master")
                .clientId("admin-cli")
                .username(keycloakAdmin)
                .password(keycloakAdminPassword)
                .build();
        return keycloak.tokenManager().getAccessToken().getToken();
    }

    @Override
    public List<KeycloakProfileDto> getProfiles() {
        log.info("Getting groups from Keycloak");
        String token = getToken();
        OkHttpClient createUserHttp = new OkHttpClient();
        Response response = null;
        Set<String> disallowedGroupIds = new HashSet<>(Arrays.asList(notAllowedGroups.split(",")));

        try {
            Request requestGetGroups = new Request.Builder()
                    .url(serverUrl + "admin/realms/" + prixpresRealm
                            + "/groups")
                    .addHeader(AUTHORIZATION, BEARER + token)
                    .build();
            response = createUserHttp.newCall(requestGetGroups).execute();
            if (!response.isSuccessful()) {
                throw new ApiRequestException("FAILED_TO_RETRIEVE_GROUPS_FROM_KEYCLOAK", "400");
            }
            List<KeycloakProfileDto> keycloakProfileDtos = new ArrayList<>();
            if (response.body() != null) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String groupId = jsonObject.getString("id");
                    if (disallowedGroupIds.contains(groupId)) {
                        continue;
                    }
                    KeycloakProfileDto keycloakProfileDto = new KeycloakProfileDto();
                    keycloakProfileDto.setId(groupId);
                    keycloakProfileDto.setName(jsonObject.getString("name"));
                    keycloakProfileDtos.add(keycloakProfileDto);
                }
            }
            return keycloakProfileDtos;
        } catch (Exception e) {
            throw new ApiRequestException("FAILED_TO_RETRIEVE_GROUPS_FROM_KEYCLOAK", "400");
        }
    }
}