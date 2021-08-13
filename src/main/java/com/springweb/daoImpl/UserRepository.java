package com.springweb.daoImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springweb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUserId(String userName);
}
