package com.anhvt.cosmetic.RestControllerAPI;


import com.anhvt.cosmetic.Entity.Order;
import com.anhvt.cosmetic.Entity.OrderDetail;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Model.OrderDetailRequest;
import com.anhvt.cosmetic.Model.OrderRequest;
import com.anhvt.cosmetic.Service.OrderDetailService;
import com.anhvt.cosmetic.Service.OrderService;
import com.anhvt.cosmetic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAll(){
        Iterable<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/user-id={id}")
    public ResponseEntity<Iterable<Order>> getByUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            Iterable<Order> orders = orderService.findByUser(id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        // Lấy thông tin đơn hàng từ request
        User user = orderRequest.getUser();
        String orderDate = orderRequest.getOrderDate();
        String deliveryDate = orderRequest.getDeliveryDate();
        String note = orderRequest.getNote();
        List<OrderDetailRequest> orderDetailRequests = orderRequest.getOrderDetails();

        // Tạo đối tượng Order mới
        Order order = new Order();
        order.setUser(user);
        order.setOrder_date(orderDate);
        order.setDelivery_date(deliveryDate);
        order.setNote(note);
        Order savedOrder = orderService.save(order);

        // Tạo đối tượng OrderDetail mới cho mỗi sản phẩm trong đơn hàng
        for (OrderDetailRequest orderDetailRequest : orderDetailRequests) {
            Product product = orderDetailRequest.getProduct();
            int quantity = orderDetailRequest.getQuantity();
            float price = product.getPrice();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);
            orderDetailService.save(orderDetail);
        }
        // Trả về response thành công với thông tin đơn hàng mới
        return ResponseEntity.ok(savedOrder);
    }
}
