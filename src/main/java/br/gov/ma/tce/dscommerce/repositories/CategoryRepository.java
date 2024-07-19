package br.gov.ma.tce.dscommerce.repositories;

import br.gov.ma.tce.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
