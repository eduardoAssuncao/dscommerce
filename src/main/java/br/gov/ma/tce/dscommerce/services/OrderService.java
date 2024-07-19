package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.dto.OrderDTO;
import br.gov.ma.tce.dscommerce.dto.OrderItemDTO;
import br.gov.ma.tce.dscommerce.dto.ProductDTO;
import br.gov.ma.tce.dscommerce.entities.*;
import br.gov.ma.tce.dscommerce.repositories.OrderItemRepository;
import br.gov.ma.tce.dscommerce.repositories.OrderRepository;
import br.gov.ma.tce.dscommerce.repositories.ProductRepository;
import br.gov.ma.tce.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;
    private UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order entity = new Order();
        entity.setMoment(Instant.now());
        entity.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        entity.setClient(user);

        for(OrderItemDTO itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem orderItem = new OrderItem(entity, product, itemDto.getQuantity(), product.getPrice());
            entity.getOrderItems().add(orderItem);
        }
        orderRepository.save(entity);
        orderItemRepository.saveAll(entity.getOrderItems());
        return new OrderDTO(entity);
    }
}
