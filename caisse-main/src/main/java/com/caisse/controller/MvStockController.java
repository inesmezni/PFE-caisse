package com.caisse.controller;

import com.caisse.dto.MvStockDto;
import com.caisse.service.IService.MvStockServiceImpl;
import com.caisse.service.MvStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Stock")

public class MvStockController {
    private final MvStockService mvStockService;

    @Autowired
    public MvStockController(MvStockService mvStockService) {
        this.mvStockService = mvStockService;
    }

    @GetMapping("/stockReel/{idArticle}")
    public BigDecimal stockReelArticle(@PathVariable Integer idArticle) {
        return mvStockService.stockReelArticle(idArticle);
    }

    @GetMapping("/mvStock/{idArticle}")
    public List<MvStockDto> mvStockArticle(@PathVariable Integer idArticle) {
        return mvStockService.mvStockArticle(idArticle);
    }

    @PostMapping("/entreeStock")
    public MvStockDto entreeStock(@RequestBody MvStockDto dto) {
        return mvStockService.entreeStock(dto);
    }

    @PostMapping("/sortieStock")
    public MvStockDto sortieStock(@RequestBody MvStockDto dto) {
        return mvStockService.sortieStock(dto);
    }
}

