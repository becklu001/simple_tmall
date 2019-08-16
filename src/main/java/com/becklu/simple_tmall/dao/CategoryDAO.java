package com.becklu.simple_tmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becklu.simple_tmall.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
