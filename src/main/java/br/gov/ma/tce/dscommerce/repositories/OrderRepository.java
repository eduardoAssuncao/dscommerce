package br.gov.ma.tce.dscommerce.repositories;

import br.gov.ma.tce.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
