package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.DTO.OrderDTO;
import com.anhvt.cosmetic.DTO.OrderDetailDTO;
import com.anhvt.cosmetic.Entity.Order;
import com.anhvt.cosmetic.Service.OrderDetailService;
import com.anhvt.cosmetic.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.anhvt.cosmetic.Mapper.OrderMapper.convertToOrderDTO;
import static com.anhvt.cosmetic.Mapper.OrderMapper.convertToOrderDTOs;
import static com.anhvt.cosmetic.Mapper.OrderDetailMapper.convertToOrderDetailDTOs;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/list")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView("admin/order/list");
        Iterable<OrderDTO> orderDTOS = convertToOrderDTOs(orderService.findAll());
        modelAndView.addObject("orders", orderDTOS);
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/order/edit");
        Optional<Order> orderDTO = orderService.findByID(id);
        if (!orderDTO.isPresent()){
            return new ModelAndView("admin/404");
        }
        Iterable<OrderDetailDTO> orderDetails = convertToOrderDetailDTOs(orderDetailService.findByOrder(id));
        OrderDTO.Status[] enumStatus = OrderDTO.Status.values();
        modelAndView.addObject("enums", enumStatus);
        modelAndView.addObject("orderDetails", orderDetails);
        modelAndView.addObject("order", orderDTO);
        modelAndView.addObject("total", orderDetailService.totalPrice(orderDetailService.findByOrder(id)));
        return modelAndView;
    }
}
