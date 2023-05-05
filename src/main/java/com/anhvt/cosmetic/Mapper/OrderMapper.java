package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.OrderDTO;
import com.anhvt.cosmetic.Entity.Order;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.DTO.OrderDTO.Status.fromValue;
import static com.anhvt.cosmetic.Utils.ConvertDate.TimestampToDate;


public class OrderMapper {
    public static OrderDTO convertToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(order.getUser());
        orderDTO.setOrder_date(TimestampToDate(order.getOrder_date()));
        orderDTO.setDelivery_date(TimestampToDate(String.valueOf((Long.parseLong(order.getOrder_date())) + 259200)));
        orderDTO.setNote(order.getNote());
        orderDTO.setStatus(fromValue(order.getStatus()));

        return orderDTO;
    }

    public static Iterable<OrderDTO> convertToOrderDTOs(Iterable<Order> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
                .map(OrderMapper::convertToOrderDTO)
                .collect(Collectors.toList());
    }
}
