package com.sanjib.edureka.ms_catalog_service;

public interface ProductService {

	public Product addProductToCatalog(String token, Product product);
	
	public Product getProductById(Integer id);
	
	public Product getProductByName(String name);
	

	/*public Product getProductFromCatalogById(Integer id);

	public String deleteProductFromCatalog(Integer id);

	public Product updateProductIncatalog(Product product);
	
	public List<Product> getAllProductsIncatalog();
	
	public List<Product> getAllProductsOfSeller(Integer id);
	
	public List<Product> getProductsOfCategory(CategoryEnum catenum);
	
	public List<Product> getProductsOfStatus(ProductStatus status);
	
	public Product updateProductQuantityWithId(Integer id,Product product);*/

}
