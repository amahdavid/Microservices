package org.microservicetest.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.ordermicroservices.dto.UserDTO;
import org.ordermicroservices.model.Order;
import org.ordermicroservices.repository.OrderRepository;
import org.ordermicroservices.service.OrderService;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testCreateOrder_UserNotFound() {
        Order order = new Order();
        order.setUserId(1L);

        when(restTemplate.getForObject("http://localhost:8081/users/1", UserDTO.class)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> orderService.createOrder(order));
    }

    @Test
    void testCreateOrder_UserFound() {
        Order order = new Order();
        order.setUserId(1L);

        UserDTO user = new UserDTO();
        when(restTemplate.getForObject("http://localhost:8081/users/1", UserDTO.class)).thenReturn(user);
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertNotNull(createdOrder);
        verify(orderRepository, times(1)).save(order);
    }

}
