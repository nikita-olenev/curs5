package com.NikitaOlenev.butchershop.repository;

import com.NikitaOlenev.butchershop.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByName(String username);
}
