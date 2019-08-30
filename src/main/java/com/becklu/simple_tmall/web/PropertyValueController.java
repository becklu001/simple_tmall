package com.becklu.simple_tmall.web;

import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becklu.simple_tmall.pojo.Product;
import com.becklu.simple_tmall.pojo.PropertyValue;
import com.becklu.simple_tmall.service.ProductService;
import com.becklu.simple_tmall.service.PropertyValueService;

@RestController
public class PropertyValueController {
	@Autowired PropertyValueService propertyValueService;
	@Autowired ProductService productService;
	
	@GetMapping("propertyValues")
	public List<PropertyValue> list(@RequestParam(value="pid") int pid){
		System.out.println("PV controller:pid="+pid);
		Product product = productService.getProduct(pid);
		System.out.println(product.getName());
		
		List <PropertyValue> list = propertyValueService.list(product);
		if(list.isEmpty()){
			System.out.println("list is empty");
		}
		for(PropertyValue pv:list){
			System.out.println(pv.getProperty().getName());
		}
		System.out.println("test-------------");
		return list;
	}
	
	@PutMapping("propertyValues")
	public PropertyValue update(@RequestBody PropertyValue bean){
		propertyValueService.update(bean);
		return bean;
	}
}
