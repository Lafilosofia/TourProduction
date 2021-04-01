package com.lhz.tourproduct.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhz.tourproduct.common.web.PageObject;
import com.lhz.tourproduct.product.entity.Product;
import com.lhz.tourproduct.product.service.ProductService;
import com.lhz.tourproduct.project.commons.ControllerException;
import com.lhz.tourproduct.project.commons.JsonResult;

@Controller
@RequestMapping("/product")
public class ProductController extends ControllerException{
	@Resource
	private ProductService productService;
	@RequestMapping("/productUI")
	public String product(){
		return "product/product_list";
	}
	
	@RequestMapping("/productEdit")
	public String productEdit(){
		return "product/product_edit";
	}
	
	/**��ѯProduct������ʾ��TreeTable��*/
	@RequestMapping("/toListProduct")
	@ResponseBody
	public JsonResult <List<Map<String,Object>>> doFindProductObject(){
		List<Map<String,Object>> list=productService.findProductObject();
		return new JsonResult<List<Map<String,Object>>>(list);
}
	
	/**��ѯProduct������ʾ��zTree��*/
	@RequestMapping("/toFindZtreeProductNodes")
	@ResponseBody
	public JsonResult<List<Product>> doFindZTreeProduct(){
		List<Product> list=productService.findZTreeProduct();
		return new JsonResult<List<Product>>(list);
	}
	
	/**ͨ��id����Product��parentName*/
	@RequestMapping("/toFindByIdProduct")
	@ResponseBody
	public JsonResult<Map<String,Object>> doFindByIdProduct(Integer id){
		Map<String,Object> map=productService.findByIdProduct(id);
		return new JsonResult<Map<String,Object>>(map);
	}
	
	/**ͨ��id�޸�Product����*/
	@RequestMapping("/toUpdateProduct")
	@ResponseBody
	public JsonResult<Integer> doUpdateProduct(Product product){
		int rows=productService.updateProduct(product);
		return new JsonResult<Integer>(rows);
	}
	
	/**����Product����*/
	@RequestMapping("/toSaveProduct")
	@ResponseBody
	public JsonResult<Integer> doSavaProduct(Product product){
		int rows=productService.addProduct(product);
		return new JsonResult<Integer>(rows);
	}
	
	/**ͨ��idɾ��Product����*/
	@RequestMapping("/toDeleteByIdProduct")
	@ResponseBody
	public JsonResult<Integer> doDeleteByIdProduct(Integer id){
		int rows=productService.deleteProduct(id);
		return new JsonResult<Integer>(rows);
	}
	
	/**��ҳ��ѯ*/
	@RequestMapping("/toPageProduct")
	@ResponseBody
	public JsonResult<PageObject> doPageProduct(Integer currentPage,String name){
		PageObject pageObject=productService.findPageObject(currentPage,name);
		return new JsonResult<PageObject>(pageObject);
	}
}
