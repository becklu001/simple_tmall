package com.becklu.simple_tmall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becklu.simple_tmall.pojo.Property;
import com.becklu.simple_tmall.service.PropertyService;

@RestController
public class PropertyController {
	@Autowired PropertyService propertyService;
	
	@GetMapping("categories/{cid}/properties")
	public Page<Property> list(@PathVariable("cid") int cid,
			@RequestParam(value="start",defaultValue="0")int start,
			@RequestParam(value="size",defaultValue="5")int size)
	throws Exception{
		start = start<0?0:start;
		Page<Property> page = propertyService.list(cid, start, size);
		return page;
	}
	
	@PostMapping("properties")
	public Property add(@RequestBody Property bean){
		System.out.println(bean.getName());
		System.out.println(bean.getCategory());
		propertyService.add(bean);
		return bean;
	}
}
