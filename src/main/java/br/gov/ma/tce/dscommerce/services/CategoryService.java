package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.dto.CategoryDTO;
import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.dto.ProductMinDTO;
import br.gov.ma.tce.dscommerce.entities.Category;
import br.gov.ma.tce.dscommerce.entities.Product;
import br.gov.ma.tce.dscommerce.repositories.CategoryRepository;
import br.gov.ma.tce.dscommerce.repositories.ProductRepository;
import br.gov.ma.tce.dscommerce.services.exceptions.DatabaseException;
import br.gov.ma.tce.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true) //readOnly -> look de apenas leitura
    public List<CategoryDTO> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(CategoryDTO::new).toList();
    }
}
