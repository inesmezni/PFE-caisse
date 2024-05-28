package com.caisse.dto;

import com.caisse.entity.Facture;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class FactureDto {
    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneFactureDto> ligneFacture;

    private Integer idEntreprise;

    public static FactureDto fromEntity(Facture facture) {
        if (facture == null) {
            return null;
        }
        return FactureDto.builder()
                .id(facture.getId())
                .code(facture.getCode())
                .commentaire(facture.getCommentaire())
                .build();
    }

    public static Facture toEntity(FactureDto dto) {
        if (dto == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(dto.getId());
        facture.setCode(facture.getCode());
        facture.setCommentaire(dto.getCommentaire());
        return facture;
    }
}
