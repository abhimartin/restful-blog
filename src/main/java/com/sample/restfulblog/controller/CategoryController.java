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
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Category entity) {
		
		categoryService.create(entity);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Category entity) {
		
		Assert.notNull(entity.getId(), "Category ID is null.");
		Assert.notNull(categoryService.findById(entity.getId()), "Category not found");
		
		categoryService.update(entity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		categoryService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Category> find() {
		
		return categoryService.find();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Category findCategory(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return categoryService.findById(id);
	}
	
	@RequestMapping(value = "/{id}/post", method = RequestMethod.GET)
	public @ResponseBody List<Post> findCategoryPosts(@PathVariable("id") Long id ) {
		
		Assert.notNull(id, "Missing ID parameter.");
		
		return postService.findPostsByCategoryId(id);
	}
}
