package com.springboot.demo.service;

import com.springboot.demo.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository< UserAuthority, Long > {
}
