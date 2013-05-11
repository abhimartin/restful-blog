package com.benjsicam.restfulblog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.benjsicam.restfulblog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query
	Category findByName(String name);
	
	@Query("SELECT category.id, category.name FROM Category category JOIN category.posts post WHERE post.id=?1")
	List<Category> findCategoriesByPostId(Long id);
}
