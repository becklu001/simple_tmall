package com.becklu.simple_tmall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.pojo.Product;

public interface ProductDAO extends JpaRepository<Product,Integer>{
	public Page<Product> findByCategory(Category category,Pageable pageable);
}
