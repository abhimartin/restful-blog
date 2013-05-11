package com.sample.restfulblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sample.restfulblog.bean.PasswordChangeDTO;
import com.sample.restfulblog.domain.Author;
import com.sample.restfulblog.domain.Post;
import com.sample.restfulblog.service.AuthorService;

@Controller
@RequestMapping("/resources/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Author entity) {
		
		authorService.create(entity);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Author entity) {
		
		Assert.notNull(entity.getId(), "Author ID is null.");
		Assert.notNull(authorService.findById(entity.getId()), "Author not found");
		
		authorService.update(entity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		authorService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Author> find() {
		
		return authorService.find();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Author findAuthor(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return authorService.findById(id);
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public @ResponseBody List<Post> findAuthorPosts(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return authorService.findAuthorPosts(id);
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updatePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Author author = authorService.findByUsername(userName);
		
		Assert.notNull(author, "No user found.");
		Assert.isTrue(passwordEncoder.matches(passwordChangeDTO.getNewPassword(), author.getPassword()), "Password is incorrect.");
		Assert.isTrue(passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword()), "Passwords do not match.");
		
		author.setPassword(passwordChangeDTO.getNewPassword());
		
		authorService.updatePassword(author);
	}	
}
