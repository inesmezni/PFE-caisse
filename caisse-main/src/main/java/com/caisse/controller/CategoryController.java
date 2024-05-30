package com.caisse.controller;

import com.caisse.dto.CategoryDto;
import com.caisse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @PostMapping( "/Save")
    public CategoryDto save(@RequestBody CategoryDto dto) {
        return categoryService.save(dto);
  }

    @PutMapping("/put/{idCategory}")
    public CategoryDto updateCategory(@PathVariable("idCategory") Integer idCategory,
                                      @RequestBody CategoryDto updatedCategoryDto) {
        return categoryService.updateCategory(idCategory) ;
    }

    @GetMapping(value = "/categories/{idCategory}")
    public CategoryDto findById(@PathVariable("idCategory") Integer idCategory) {
        return categoryService.findById(idCategory);
    }

    @GetMapping(value = "/categories/filter/{codeCategory}")
    public CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory) {
        return categoryService.findByCode(codeCategory);
    }

    @GetMapping(value = "/categories/all")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @DeleteMapping(value = "/deleteCat/{idCategory}")
    public void delete(@PathVariable("idCategory") Integer id) {
        categoryService.delete(id);

    }
}
