package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.entities.Product;
import br.gov.ma.tce.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true) //readOnly -> look de apenas leitura
    public ProductDTO findById(Integer id) {
        /*Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;*/
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true) //readOnly -> look de apenas leitura
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        //converter em uma lista de ProductDTO. Pra cada registro da minha lista original, irei chamar o new ProductDTO recebendo x e depois converto para Lista
        //O Page já é um stream do java
        return result.map(x -> new ProductDTO(x));
    }
}
