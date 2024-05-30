package com.caisse.dto;

import com.caisse.entity.LigneFacture;
import lombok.*;

import java.math.BigDecimal;
@Data
@Builder
public class LigneFactureDto {
    private Integer id;

    private FactureDto facture;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    public static LigneFactureDto fromEntity(LigneFacture ligneFacture) {
        if (ligneFacture == null) {
            return null;
        }

        return LigneFactureDto.builder()
                .id(ligneFacture.getId())
                .facture(FactureDto.fromEntity(ligneFacture.getFacture()))
                .article(ArticleDto.fromEntity(ligneFacture.getArticle()))
                .quantite(ligneFacture.getQuantite())
                .prixUnitaire(ligneFacture.getPrixUnitaire())
                .build();
    }

    public static LigneFacture toEntity(LigneFactureDto dto) {
        if (dto == null) {
            return null;
        }
        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setId(dto.getId());
        ligneFacture.setFacture(FactureDto.toEntity(dto.getFacture()));
        ligneFacture.setArticle(ArticleDto.toEntity(dto.getArticle()));
        ligneFacture.setQuantite(dto.getQuantite());
        ligneFacture.setPrixUnitaire(dto.getPrixUnitaire());
        return ligneFacture;
    }

}