package com.caisse.service;

import com.caisse.dto.MvStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvStockService {
    BigDecimal stockReelArticle(Integer idArticle);

    List mvStockArticle(Integer idArticle);
    MvStockDto entreeStock(MvStockDto dto);

    MvStockDto sortieStock(MvStockDto dto);

 //   MvStockDto corecctionStockPOS(MvStockDto dto);
  //  MvStockDto corecctionStockNEG(MvStockDto dto);



}
