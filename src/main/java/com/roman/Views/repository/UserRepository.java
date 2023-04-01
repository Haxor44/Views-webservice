package com.roman.Views.repository;

import com.roman.Views.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Integer> {
    public Optional<AppUser> findByEmail(String email);
}
