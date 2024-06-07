package br.gov.ma.tce.dscommerce.controllers;

import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //http://localhost:8080/products?size=12 -> argumento para que haja o retorno de apenas 12 objetos por página
    //http://localhost:8080/products?size=12&page=1 -> argumento para que retorne à partir da segunda página
    //http://localhost:8080/products?size=12&page=0&sort=name,desc -> argumento para retornar ordenado por nome e decrescente
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }
}
