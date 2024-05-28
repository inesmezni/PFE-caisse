package com.caisse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakProfileDto {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;

    public KeycloakProfileDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
