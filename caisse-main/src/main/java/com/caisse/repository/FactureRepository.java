package com.caisse.repository;

import com.caisse.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Integer> {

    Optional<Facture> findFactureByCode(String code);
}
