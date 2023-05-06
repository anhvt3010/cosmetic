package com.anhvt.cosmetic.Repository;

import com.anhvt.cosmetic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query( "SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword% " )
    Optional<User> getUserByUsername(String keyword);
}
