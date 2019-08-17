package com.becklu.simple_tmall.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.becklu.simple_tmall.pojo.Category;
import com.becklu.simple_tmall.service.CategoryService;
import com.becklu.simple_tmall.util.ImageUtil;

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
	
	@PostMapping("categories")
	public Category add(Category bean,MultipartFile image,HttpServletRequest request)
	throws Exception{
		//前端函数 add()中的交互为 axios.post(url,formData)
		//此处如何填充Category bean 参数呢？？这是个疑问
		//前端 formData.append("name",this.bean.name) 自动填充
		//但是要注意，字段名称 "name"要填写的和 category类成员名称一样
		//不能随意填，要不然取不到参数
		
		//而且注意注入 image的方式
		
		System.out.println("CC.add()");
		System.out.println("bean.description="+bean.getDescription());
		System.out.println("bean.name="+bean.getName());
		
		categoryService.add(bean);
		//数据库中并不存放图片，图片应该存放在专门的服务器上
		ImageUtil.saveOrUpdateImageFile(bean,image,request);
		return bean;
	}
	
	@DeleteMapping("categories/{id}")
	public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
		System.out.println("CC delete():");
		categoryService.delete(id);
//		File image = new File("img/category/"+id+".jpg");
		String imagePath = request.getServletContext()
				.getRealPath("img/category/")+id+".jpg";
		System.out.println(imagePath);
		
		File image = new File(imagePath);
		
		if(!(image.delete())){
			System.out.println("error while deleting category image");
		}
		return null;
	}
	
	@GetMapping("categories/{id}")
	public Category get(@PathVariable("id") int id){
		return categoryService.get(id);
	}
	
	//出现了一个特别奇怪的问题，如果 PutMapping的url只是  categories 那么
	//传递的参数将不符合格式，应该是没有 id，取不到合适的bean
	//导致最终的 categoryDAO.save(bean)变成了 insert
	//因为不带 id的话 JPA将会把save解析为 insert操作
	@PutMapping("categories/{id}")
	public Category update(Category bean,MultipartFile image,HttpServletRequest request) throws Exception{
		System.out.println("CC update():");
		String name = request.getParameter("name");
		String description = request.getParameter("description"); 
		
		System.out.println(name);
		System.out.println(description);
		
//		Category bean = new Category();
		bean.setName(request.getParameter("name"));
		bean.setDescription(request.getParameter("description"));
		
		categoryService.update(bean);
		//最好检查添加图片是否成功
		ImageUtil.saveOrUpdateImageFile(bean, image, request);
		
		return bean;
		
	}
}
