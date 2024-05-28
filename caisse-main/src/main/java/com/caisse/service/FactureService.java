package com.caisse.service;

import com.caisse.dto.FactureDto;

import java.util.List;

public interface FactureService {



    FactureDto save(FactureDto dto);

    FactureDto findById(Integer id);

    FactureDto findByCode(String code);

    List<FactureDto> findAll();

    void delete(Integer id);
}
