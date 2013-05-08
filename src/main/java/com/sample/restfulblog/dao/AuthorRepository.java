package com.sample.restfulblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.restfulblog.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	@Query
	Author findByUsername(String username);
	
	@Query
	Author findByUsernameAndPassword(String username, String password);
}
