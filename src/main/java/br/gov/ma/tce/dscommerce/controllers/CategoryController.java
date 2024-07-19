package br.gov.ma.tce.dscommerce.controllers;

import br.gov.ma.tce.dscommerce.dto.CategoryDTO;
import br.gov.ma.tce.dscommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//o @RequiredArgsContructor não tá funcionando
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> dto = categoryService.findAll();
        return ResponseEntity.ok(dto);
    }
}
