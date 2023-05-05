package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.OrderDetailDTO;
import com.anhvt.cosmetic.Entity.OrderDetail;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OrderDetailMapper {
    public static OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setProduct(orderDetail.getProduct());
        orderDetailDTO.setOrder(orderDetail.getOrder());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());

        return orderDetailDTO;
    }

    public static Iterable<OrderDetailDTO> convertToOrderDetailDTOs(Iterable<OrderDetail> orderDetails) {
        return StreamSupport.stream(orderDetails.spliterator(), false)
                .map(OrderDetailMapper::convertToOrderDetailDTO)
                .collect(Collectors.toList());
    }
}
