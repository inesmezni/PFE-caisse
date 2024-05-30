package com.caisse.dto;

import com.caisse.entity.Facture;
import com.caisse.entity.Type_paiement;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class FactureDto {
    private Integer id;
    private Instant creationDate;

    private String code;

    //  private Instant dateVente;

    private String commentaire;

    private Type_paiement typePaiement;

    private List<LigneFactureDto> ligneFacture;

    private Integer idEntreprise;

    public static FactureDto fromEntity(Facture facture) {
        if (facture == null) {
            return null;
        }
        return FactureDto.builder()
                .id(facture.getId())
                .code(facture.getCode())
                .creationDate(facture.getCreationDate())
                .commentaire(facture.getCommentaire())
                .typePaiement(facture.getType_paiement())
                .build();
    }

    public static Facture toEntity(FactureDto dto) {
        if (dto == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(dto.getId());
        facture.setCode(dto.getCode());
        facture.setCreationDate(facture.getCreationDate());
        facture.setCommentaire(dto.getCommentaire());
        facture.setType_paiement(dto.getTypePaiement());
        return facture;
    }
}
