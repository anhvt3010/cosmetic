package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "select * from order_detail where order_id = ?1", nativeQuery = true)
    Iterable<OrderDetail> findByOrder(Long id);
}
