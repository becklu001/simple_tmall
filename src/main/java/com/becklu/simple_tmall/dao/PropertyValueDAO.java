package com.becklu.simple_tmall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becklu.simple_tmall.pojo.Product;
import com.becklu.simple_tmall.pojo.Property;
import com.becklu.simple_tmall.pojo.PropertyValue;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{
	public List<PropertyValue> findByProductOrderByIdDesc(Product product);
	public List<PropertyValue> findByProductAndProperty(Product product,Property property);
	public PropertyValue getByProductAndProperty(Product product,Property property);
}
