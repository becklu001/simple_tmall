package com.becklu.simple_tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.becklu.simple_tmall.dao.CategoryDAO;
import com.becklu.simple_tmall.pojo.Category;

@Service
public class CategoryService {
	@Autowired CategoryDAO categoryDAO;
	
	public Page<Category> list(int start,int size){
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(start,size,sort);
		Page<Category> page = categoryDAO.findAll(pageable);
		return page;
		
	} 
}
