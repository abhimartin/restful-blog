package com.benjsicam.restfulblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benjsicam.restfulblog.dao.CategoryRepository;
import com.benjsicam.restfulblog.domain.Category;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional
	public void create(Category category) {		
		categoryRepository.saveAndFlush(category);
	}
	
	@Transactional
	public void update(Category category) {		
		categoryRepository.saveAndFlush(category);
	}
	
	@Transactional
	public void delete(Long id) {
		categoryRepository.delete(id);
	}
	
	public List<Category> find() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public List<Category> findCategoriesByPostId(Long id) {
		return categoryRepository.findCategoriesByPostId(id);
	}
}
