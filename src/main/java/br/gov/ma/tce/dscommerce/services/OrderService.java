package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.dto.OrderDTO;
import br.gov.ma.tce.dscommerce.entities.Order;
import br.gov.ma.tce.dscommerce.repositories.OrderRepository;
import br.gov.ma.tce.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }
}
