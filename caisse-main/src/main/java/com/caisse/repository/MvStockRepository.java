package com.caisse.repository;

import com.caisse.entity.MvStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvStockRepository extends JpaRepository <MvStock,Integer> {
    @Query("select sum(m.quantite) from MvStock m where m.article.id = :idArticle")
    BigDecimal stockReelArticle(@Param("idArticle") Integer idArticle);


    List<MvStock> findAllByArticleId(Integer idArticle);
}
