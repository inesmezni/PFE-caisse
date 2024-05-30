package com.caisse.repository;

import com.caisse.entity.LigneFacture;
import com.caisse.entity.LigneStockUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneStockUpRepository extends JpaRepository<LigneStockUp,Integer> {
}
