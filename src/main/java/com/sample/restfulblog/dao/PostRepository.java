package com.sample.restfulblog.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.restfulblog.domain.Author;
import com.sample.restfulblog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {	
	@Query
	List<Post> findByAuthor(Author author);
	
	@Query
	List<Post> findByDate(Date date);
	
	@Query("SELECT post.id, post.date, post.content FROM Post post JOIN post.categories category WHERE category.id=?1")
	List<Post> findPostsByCategoryId(Long id);
}
