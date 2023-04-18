package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query(value = "select * from delivery where user_id = ?1", nativeQuery = true)
    Iterable<Delivery> findByUser(Long id);
}
