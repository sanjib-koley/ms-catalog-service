package com.sanjib.edureka.ms_catalog_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductReository extends JpaRepository<Product, Integer> {
	
	public Product findProductByProductId(Integer productId);
	
	
	//public List<Product> getAllProductsInACategory(@Param("catenum") CategoryEnum catenum);
	
	//public List<Product> getProductsWithStatus(@Param("status") ProductStatus status);
	
	//public List<Product> getProductsOfASeller(@Param("id") Integer id);
	

}
