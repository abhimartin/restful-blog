package com.sample.restfulblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.restfulblog.dao.AuthorRepository;
import com.sample.restfulblog.dao.PostRepository;
import com.sample.restfulblog.domain.Author;
import com.sample.restfulblog.domain.Post;

@Service
public class AuthorService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void create(Author author) {
		author.setPassword(passwordEncoder.encode(author.getPassword()));
		authorRepository.saveAndFlush(author);
	}
	
	@Transactional
	public void update(Author author) {
		author = this.findById(author.getId());
		
		author.setFirstName(author.getFirstName());
		author.setLastName(author.getLastName());
		
		authorRepository.saveAndFlush(author);
	}

	@Transactional
	public void delete(Long id) {
		authorRepository.delete(id);
	}
	
	public List<Author> find() {
		return authorRepository.findAll();
	}
	
	public Author findById(Long id ) {
		return authorRepository.findOne(id);
	}
	
	public List<Post> findAuthorPosts(Long id ) {
		return postRepository.findByAuthor(authorRepository.findOne(id));
	}
	
	public Author findByUsername(String username) {
		return authorRepository.findByUsername(username);
	}
	
	@Transactional
	public void updatePassword(Author author) {
		author.setPassword(passwordEncoder.encode(author.getPassword()));
		authorRepository.saveAndFlush(author);
	}
}
