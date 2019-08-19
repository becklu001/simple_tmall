package com.becklu.simple_tmall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becklu.simple_tmall.pojo.Product;
import com.becklu.simple_tmall.service.ProductService;

@RestController
public class ProductController {
	@Autowired ProductService productService;
	
	@GetMapping("categories/{cid}/products")
	public Page<Product> list(@PathVariable("cid") int cid,
			@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size){
		
		start=start<0?0:start;
		Page<Product> page = productService.list(cid,start,size);
		return page;
	}
	
	@PostMapping("products")
	public Product add(@RequestBody Product bean){
		productService.add(bean);
		return bean;
	}
	
	@DeleteMapping("products/{id}")
	public String delete(@PathVariable("id") int id){
		productService.delete(id);
		return null;
	}
}
