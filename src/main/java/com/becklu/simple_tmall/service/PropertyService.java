package com.becklu.simple_tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.becklu.simple_tmall.dao.PropertyDAO;
import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.pojo.Property;

@Service
public class PropertyService {
	@Autowired PropertyDAO propertyDAO;
	@Autowired CategoryService categoryService;
	
	public Page<Property> list(int cid,int start,int size){
		Category category = categoryService.get(cid);
		
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = new PageRequest(start,size,sort);
		
		Page<Property> page = propertyDAO.findByCategory(category, pageable);
		
		return page;
		
	}
	
	public void add(Property bean){
		System.out.println("propertyService.add()");
		propertyDAO.save(bean);
	}
	
}
