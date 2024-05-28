package com.caisse.service;

import com.caisse.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto dto);


    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

  //  List<LigneFactureDto> findHistoriqueVentes(Integer idArticle);


 //   List<LigneStockUpDto> findHistoriqueStockUp(Integer idArticle);

    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

    void delete(Integer id);



}
