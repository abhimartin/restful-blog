package com.sample.restfulblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sample.restfulblog.domain.Category;
import com.sample.restfulblog.domain.Post;
import com.sample.restfulblog.service.CategoryService;
import com.sample.restfulblog.service.PostService;

@Controller
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)	
	public void create(@RequestBody Post post) {
		
		postService.create(post);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Post post) {
		Assert.notNull(post.getId(), "Post ID is null.");
		
		postService.update(post);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	public void delete(@PathVariable("id") Long id) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		postService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)	
	public @ResponseBody List<Post> find() {
		
		return postService.find();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public @ResponseBody Post findPost(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return postService.findById(id);
	}
	
	@RequestMapping(value = "/{id}/category", method = RequestMethod.GET)	
	public @ResponseBody List<Category> findPostCategories(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return categoryService.findCategoriesByPostId(id);
	}
	
}