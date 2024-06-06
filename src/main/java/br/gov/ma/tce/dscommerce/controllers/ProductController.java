package br.gov.ma.tce.dscommerce.controllers;

import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.entities.Product;
import br.gov.ma.tce.dscommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
//o @RequiredArgsContructor não tá funcionando
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Integer id){
        return productService.findById(id);
    }
}
