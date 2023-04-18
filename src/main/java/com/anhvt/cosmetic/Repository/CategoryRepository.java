package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from category where parent_id = 0", nativeQuery = true)
    Iterable<Category> findByParent();

    @Query(value = "select * from category where id = ?1", nativeQuery = true)
    Optional<Category> findByParentID(Long id);

    @Query(value = "select * from category where parent_id != 0", nativeQuery = true)
    Iterable<Category> findByChild();

    @Query(value = "select * from category where parent_id != 0", nativeQuery = true)
    Page<Category> findByChild(Pageable pageable);
}
