package com.becklu.simple_tmall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.becklu.simple_tmall.dao.ProductDAO;
import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.pojo.Product;

@Service
public class ProductService {
	@Autowired ProductDAO productDAO;
	@Autowired CategoryService categoryService;
	public Page<Product> list(int cid,int start,int size){
		Category category = categoryService.get(cid);
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(start,size,sort);
		
		Page<Product> page = productDAO.findByCategory(category, pageable);
		return page;
	}
	
	public Product getProduct(int id){
		return productDAO.getOne(id);
	}
	
	public void add(Product bean){
		productDAO.save(bean);
	}
	
	public void delete(int id){
		productDAO.delete(id);
	}
	
	public void updateproduct(Product bean){
		productDAO.save(bean);
	}
	
	
}
