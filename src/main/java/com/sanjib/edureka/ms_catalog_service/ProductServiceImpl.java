package com.sanjib.edureka.ms_catalog_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductReository productReository;
	
	
	@Autowired
    TokenService tokenService;

	@Override
	public Product addProductToCatalog(String token, Product product) {
		Long sellerId = tokenService.getUserIdFromToken(token);
		product.setSellerId(sellerId);
		productReository.save(product);
		return product;
	}
	
	@Override
	public Product getProductById(Integer id) {
		return productReository.findProductByProductId(id);
	}
	
	public Product getProductByName(String name) {
		return productReository.findProductByProductName(name);
	}
	
	public Product updateProduct(Product product) {
		return productReository.save(product);
	}
/*
	@Override
	public Product getProductFromCatalogById(Integer id)  {

		Optional<Product> opt = productReository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}

		else
			throw new RuntimeException("Product not found with given id");
	}

	@Override
	public String deleteProductFromCatalog(Integer id) {
		Optional<Product> opt = productReository.findById(id);
		
		if (opt.isPresent()) {
			Product prod = opt.get();
			System.out.println(prod);
			productReository.delete(prod);
			return "Product deleted from catalog";
		} else
			throw new RuntimeException("Product not found with given id");

	}

	@Override
	public Product updateProductIncatalog(Product prod) {

		Optional<Product> opt = productReository.findById(prod.getProductId());

		if (opt.isPresent()) {
			opt.get();
			Product prod1 = productReository.save(prod);
			return prod1;
		} else
			throw new RuntimeException("Product not found with given id");
	}

	@Override
	public List<Product> getAllProductsIncatalog() {
		List<Product> list = productReository.findAll();
		
		if (list.size() > 0) {
			return list;
		} else
			throw new RuntimeException("No products in catalog");

	}

	@Override
	public List<Product> getProductsOfCategory(CategoryEnum catenum) {

		List<Product> list = productReository.getAllProductsInACategory(catenum);
		if (list.size() > 0) {

			return list;
		} else
			throw new RuntimeException("No products found with category:" + catenum);
	}

	@Override
	public List<Product> getProductsOfStatus(ProductStatus status) {

		List<Product> list = productReository.getProductsWithStatus(status);

		if (list.size() > 0) {
			return list;
		} else
			throw new RuntimeException("No products found with given status:" + status);
	}

	@Override
	public Product updateProductQuantityWithId(Integer id,Product product) {
		 Product prod = null;
		 Optional<Product> opt = productReository.findById(id);
		 
		 if(opt!=null) {
			  prod = opt.get();
			 prod.setQuantity(prod.getQuantity()+product.getQuantity());
			 if(prod.getQuantity()>0) {
				 prod.setStatus(ProductStatus.AVAILABLE);
			 }
			 productReository.save(prod);
			 
		 }
		 else
			 throw new RuntimeException("No product found with this Id");
		
		return prod;
	}

	

	@Override
	public List<Product> getAllProductsOfSeller(Integer id) {

		List<Product> list = productReository.getProductsOfASeller(id);

		if (list.size() > 0) {
			return list;
		}else {
			throw new RuntimeException("No products with SellerId: " + id);
		}
	}
*/
}
