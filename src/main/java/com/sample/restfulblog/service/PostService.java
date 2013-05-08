package com.sample.restfulblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.restfulblog.dao.PostRepository;
import com.sample.restfulblog.domain.Post;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void create(Post post) {		
		postRepository.saveAndFlush(post);
	}
	
	@Transactional
	public void update(Post post) {		
		postRepository.saveAndFlush(post);
	}
	
	@Transactional
	public void delete(Long id) {
		postRepository.delete(id);
	}
	
	public List<Post> find() {
		return postRepository.findAll();
	}
	
	public Post findById(Long id ) {
		return postRepository.findOne(id);
	}
	
	public List<Post> findPostsByCategoryId(Long id) {
		return postRepository.findPostsByCategoryId(id);
	}
	
}
