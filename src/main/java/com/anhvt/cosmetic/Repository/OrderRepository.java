package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query(value = "select * from order where user_id = ?1", nativeQuery = true)
    Iterable<Order> findByUser(Long id);
}
