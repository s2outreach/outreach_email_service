package com.cts.outreach.email.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.outreach.email.entity.UserEntity;

public interface UserInterface extends JpaRepository<UserEntity, Long>{
	
}
