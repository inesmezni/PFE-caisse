package com.caisse.controller;
import com.caisse.dto.FactureDto;
import com.caisse.service.FactureService;
import com.caisse.service.IService.FactureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("*")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping
    public FactureDto saveFacture(@RequestBody FactureDto dto) {
        return factureService.save(dto);
    }

    @GetMapping("/{id}")
    public FactureDto getFactureById(@PathVariable Integer id) {
        return factureService.findById(id);
    }

    @GetMapping("/code/{code}")
    public FactureDto getFactureByCode(@PathVariable String code) {
        return factureService.findByCode(code);
    }

    @GetMapping
    public List<FactureDto> getAllFactures() {
        return factureService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteFacture(@PathVariable Integer id) {
        factureService.delete(id);
    }

}
