package com.springboot.demo.service;

import com.springboot.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query(value = """
            SELECT u.* FROM user_details u WHERE u.user_name = :username
            """, nativeQuery = true)
    Optional<UserDetails> findByUserName(String username);
}
