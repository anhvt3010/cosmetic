package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
}
