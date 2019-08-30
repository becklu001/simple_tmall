package com.becklu.simple_tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becklu.simple_tmall.dao.ProductDAO;
import com.becklu.simple_tmall.dao.PropertyDAO;
import com.becklu.simple_tmall.dao.PropertyValueDAO;
import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.pojo.Product;
import com.becklu.simple_tmall.pojo.Property;
import com.becklu.simple_tmall.pojo.PropertyValue;

@Service
public class PropertyValueService {

	@Autowired PropertyValueDAO propertyValueDAO;
	@Autowired ProductDAO productDAO;
	@Autowired PropertyDAO propertyDAO;
	
	public List<PropertyValue> list(Product product){
		//并非一个product在pv表中有部分属性存在就不需要初始化，需要遍历一遍，没有初始化的属性都需要初始化
		Category category = product.getCategory();
		List <Property> properties = propertyDAO.findByCategory(category);
		
		for(Property pt:properties){
			PropertyValue pv = propertyValueDAO.getByProductAndProperty(product, pt);
			//对于一个产品product，没有设置的属性都要进行初始化设置
			if(null==pv){
				pv = new PropertyValue();
				pv.setProduct(product);
				pv.setProperty(pt);
				pv.setValue(new String());
				propertyValueDAO.save(pv);
			}
		}
		
		
		List <PropertyValue> list = propertyValueDAO.findByProductOrderByIdDesc(product);
		
		return list;
	}
	
	public void update(PropertyValue bean){
		propertyValueDAO.save(bean);
	}
	
}
