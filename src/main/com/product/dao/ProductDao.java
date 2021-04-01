package com.lhz.tourproduct.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhz.tourproduct.product.entity.Product;

public interface ProductDao {
	/**查询Product数据显示在TreeTable上*/
	List<Map<String,Object>> findProductObject(@Param("indexPage")int indexPage,
											   @Param("pageSize")int pageSize,
											   @Param("name")String name);
											
	/**查询Product总页数*/
	int rowCount(@Param("name")String name);
			   
	
	/**查询Product数据显示在zTree上*/
	List<Product> findzTreeProduct();
	
	/**通过id查找Product和parentName*/
	Map<String,Object> findByIdProduct(Integer id);
	
	/**通过id查找Product数据*/
	Product pruductById(Integer id);
	
	/**通过id修改Product数据*/
	int updateProductType(Product product);
	
	/**插入Product数据*/
	int addProdcut(Product product);
	
	/**通过id查询它的子元素*/
	int hasChilds(int id);
	
	/**通过id删除product数据*/
	int deleteProduct(int id);
	
}
