package com.becklu.simple_tmall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.service.CategoryService;

@RestController
public class CategoryController {
@Autowired CategoryService categoryService;
	
	@GetMapping("categories")
	public Page<Category> list(@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size) throws Exception{
		 start=start<0?0:start;
		 Page page = categoryService.list(start,size);
		 return page;
	}
}
