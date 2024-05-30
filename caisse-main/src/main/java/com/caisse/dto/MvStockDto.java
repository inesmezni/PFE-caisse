package com.caisse.dto;

import com.caisse.entity.MvStock;
import com.caisse.entity.SourceMvtStk;
import com.caisse.entity.TypeMvtStk;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvStockDto {
    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvt;

    private SourceMvtStk sourceMvt;


    public static MvStockDto fromEntity(MvStock mvtstk) {
        if (mvtstk == null) {
            return null;
        }

        return MvStockDto.builder()
                .id(mvtstk.getId())
                .dateMvt(mvtstk.getDateMvt())
                .quantite(mvtstk.getQuantite())
                .article(ArticleDto.fromEntity(mvtstk.getArticle()))
                .typeMvt(mvtstk.getTypeMvt())
                .sourceMvt(mvtstk.getSourceMvt())
                .build();
    }

    public static MvStock toEntity(MvStockDto dto) {
        if (dto == null) {
            return null;
        }

        MvStock mvtStk = new MvStock();
        mvtStk.setId(dto.getId());
        mvtStk.setDateMvt(dto.getDateMvt());
        mvtStk.setQuantite(dto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(dto.getArticle()));
        mvtStk.setTypeMvt(dto.getTypeMvt());
        mvtStk.setSourceMvt(dto.getSourceMvt());
        return mvtStk;
    }
}
