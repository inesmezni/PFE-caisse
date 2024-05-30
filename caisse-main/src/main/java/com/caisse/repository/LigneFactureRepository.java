package com.caisse.repository;

import com.caisse.entity.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneFactureRepository extends JpaRepository <LigneFacture,Integer>{

    List<LigneFacture> findAllByArticleId(Integer idArticle);

    List<LigneFacture> findAllByFactureId(Integer id);

}
