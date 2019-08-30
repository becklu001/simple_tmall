package com.becklu.simple_tmall.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.pojo.Property;

public interface PropertyDAO extends JpaRepository<Property,Integer>{
	public Page<Property> findByCategory(Category category,Pageable pageable);
	public List<Property> findByCategory(Category category);
}
