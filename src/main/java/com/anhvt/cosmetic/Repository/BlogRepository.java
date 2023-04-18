package com.anhvt.cosmetic.Repository;


import com.anhvt.cosmetic.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
