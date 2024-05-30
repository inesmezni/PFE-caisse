package com.caisse.controller;

import com.caisse.dto.KeycloakProfileDto;
import com.caisse.service.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/keycloak")
@Slf4j
public class KeycloakController {

    @Autowired
    KeycloakService keycloakService;

    @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<KeycloakProfileDto>> getAllKeycloakProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(keycloakService.getProfiles());
    }
}