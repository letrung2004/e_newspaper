package com.newspaper.identityservice.repository;

import com.newspaper.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    @Query("SELECT u.username FROM User u WHERE u.id = :id")
    Optional<String> getUsernameById(@Param("id") String id);

}
