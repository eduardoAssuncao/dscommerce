package br.gov.ma.tce.dscommerce.controllers;

import br.gov.ma.tce.dscommerce.dto.CustomError;
import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestController
//o @RequiredArgsContructor não tá funcionando
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }

    //Quando não utilizamos o @ControllerAdvice é necessário usar o try-catch em várias partes do código.
    /*@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        try{
            ProductDTO dto = productService.findById(id);
            return ResponseEntity.ok(dto);
        }catch(Exception e){
            CustomError err = new CustomError(Instant.now(), 404, e.getMessage(), "Caminho");
            return ResponseEntity.status(404).body(err);
        }
    }*/

    //http://localhost:8080/products?size=12 -> argumento para que haja o retorno de apenas 12 objetos por página
    //http://localhost:8080/products?size=12&page=1 -> argumento para que retorne à partir da segunda página
    //http://localhost:8080/products?size=12&page=0&sort=name,desc -> argumento para retornar ordenado por nome e decrescente
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> dto = productService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        dto = productService.insert(dto);
        //Com essa prática, no cabeçario da resposta, vai ter o link do recurso criado.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO dto){
        dto = productService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
