package th.ac.ku.order.controller;


import org.springframework.web.bind.annotation.*;
import th.ac.ku.order.data.OrderRepository;
import th.ac.ku.order.model.Orders;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Orders> getAll(){
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Orders getOne(@PathVariable int id){
        return orderRepository.findById(id).get();
    }

    @GetMapping("/orders/{orderId}")
    public List<Orders> getAllOrderId(@PathVariable int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @PostMapping
    public Orders create(@RequestBody Orders customer) {
        Orders record = orderRepository.save(customer);
        orderRepository.flush();
        return record;
    }

    @PutMapping("/{id}")
    public Orders update(@PathVariable int id, @RequestBody Orders order) {
        Orders record = orderRepository.findById(id).get();
        record.setOrderStatus(order.getOrderStatus());
        record.setProductId(order.getProductId());
        record.setProductUnit(order.getProductUnit());
        orderRepository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public Orders delete(@PathVariable int id) {
        Orders record = orderRepository.findById(id).get();
        orderRepository.deleteById(id);
        return record;
    }
}
