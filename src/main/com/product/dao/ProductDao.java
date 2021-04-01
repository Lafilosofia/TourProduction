package com.lhz.tourproduct.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhz.tourproduct.product.entity.Product;

public interface ProductDao {
	/**��ѯProduct������ʾ��TreeTable��*/
	List<Map<String,Object>> findProductObject(@Param("indexPage")int indexPage,
											   @Param("pageSize")int pageSize,
											   @Param("name")String name);
											
	/**��ѯProduct��ҳ��*/
	int rowCount(@Param("name")String name);
			   
	
	/**��ѯProduct������ʾ��zTree��*/
	List<Product> findzTreeProduct();
	
	/**ͨ��id����Product��parentName*/
	Map<String,Object> findByIdProduct(Integer id);
	
	/**ͨ��id����Product����*/
	Product pruductById(Integer id);
	
	/**ͨ��id�޸�Product����*/
	int updateProductType(Product product);
	
	/**����Product����*/
	int addProdcut(Product product);
	
	/**ͨ��id��ѯ������Ԫ��*/
	int hasChilds(int id);
	
	/**ͨ��idɾ��product����*/
	int deleteProduct(int id);
	
}
