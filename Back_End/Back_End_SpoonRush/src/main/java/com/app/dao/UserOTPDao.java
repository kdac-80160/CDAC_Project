package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserOTP;

public interface UserOTPDao extends JpaRepository<UserOTP, Long> {
	Optional<UserOTP> findByUserEmail(String email);
}
