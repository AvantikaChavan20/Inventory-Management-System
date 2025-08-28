package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.Admin;
import com.user.entity.User;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByUsername(String username);
}
