package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.entities.Product;
import br.gov.ma.tce.dscommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
