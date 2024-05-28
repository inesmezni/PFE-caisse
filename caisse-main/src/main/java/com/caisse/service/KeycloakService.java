package com.caisse.service;


import com.caisse.dto.KeycloakProfileDto;

import java.util.List;


public interface KeycloakService {
    String getCurrentUserDetails();

    List<KeycloakProfileDto> getProfiles();

    String getUserInfo(String idKeyCloack);


}