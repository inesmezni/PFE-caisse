package com.caisse.dto;

import com.caisse.entity.StockUp;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class stockUpDto {
    private Integer id;

    private String code;

    private Instant dateCommande;


    //private FournisseurDto fournisseur;

    private Integer idEntreprise;
    private List<LigneStockUpDto> lignestockup;

    public static stockUpDto fromEntity(StockUp stockUp) {
        if (stockUp == null) {
            return null;
        }
        return stockUpDto.builder()
                .id(stockUp.getId())
                .code(stockUp.getCode())
                .dateCommande(stockUp.getDateCommande())

                .build();
    }

    public static StockUp toEntity(stockUpDto dto) {
        if (dto == null) {
            return null;
        }
        StockUp stockUp = new StockUp();
        stockUp.setId(dto.getId());
        stockUp.setCode(dto.getCode());
        stockUp.setDateCommande(dto.getDateCommande());

        return stockUp;
    }


}
