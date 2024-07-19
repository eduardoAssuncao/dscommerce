package br.gov.ma.tce.dscommerce.repositories;

import br.gov.ma.tce.dscommerce.entities.OrderItem;
import br.gov.ma.tce.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
