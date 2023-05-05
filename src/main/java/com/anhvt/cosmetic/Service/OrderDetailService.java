package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.DTO.OrderDetailDTO;
import com.anhvt.cosmetic.Entity.OrderDetail;
import com.anhvt.cosmetic.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Mapper.OrderDetailMapper.convertToOrderDetailDTOs;

@Service
public class OrderDetailService{
    private final OrderDetailRepository orderDetailRepository ;
    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public Iterable<OrderDetail> findAll(){
        return orderDetailRepository.findAll();
    }

    public Iterable<OrderDetail> findByOrder(Long id){
        return orderDetailRepository.findByOrder(id);
    }

    public Integer totalPrice(Iterable<OrderDetail> orderDetails) {
        Iterable<OrderDetailDTO> orderDetailDTOS = convertToOrderDetailDTOs(orderDetails);
        return StreamSupport.stream(orderDetailDTOS.spliterator(), false) // chuyển đổi Iterable thành Stream
                .mapToInt(orderDetail -> (int) orderDetail.getProduct().getPrice()) // ánh xạ từng đối tượng sang giá sản phẩm
                .sum(); // tính tổng các giá trị sản phẩm và trả về kết quả
    }


    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}