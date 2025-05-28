package com.sanjib.edureka.ms_catalog_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService pService;
	
	@Autowired
    TokenService tokenService;

	@PostMapping("/products/add")
	public ResponseEntity<?> addProductToCatalogHandler(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype, @Valid @RequestBody Product product) {

		if (tokenService.validateToken(token) && "seller".equalsIgnoreCase(usertype)) {
			Product prod = pService.addProductToCatalog(token, product);
			return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}

	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductNameById(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype,@PathVariable("id") Integer productId) {

		if (tokenService.validateToken(token) && "seller".equalsIgnoreCase(usertype)) {
			String prodName = pService.getProductById(productId).getProductName();
			return new ResponseEntity<String>(prodName, HttpStatus.OK);
		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}

	}
	
	@GetMapping("/productbyid/{id}")
	public ResponseEntity<?> getProductById(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype,@PathVariable("id") Integer productId) {

		if (tokenService.validateToken(token) && "customer".equalsIgnoreCase(usertype)) {
			Product prod = pService.getProductById(productId);
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}

	}
	
	@GetMapping("/productbyname/{name}")
	public ResponseEntity<?> getProductByName(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype,@PathVariable("name") String productName) {

		if (tokenService.validateToken(token) && "customer".equalsIgnoreCase(usertype)) {
			Product prod = pService.getProductByName(productName);
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}
	}
	
	@PutMapping("/add/quantity/{id}/{quantity}")
	public ResponseEntity<?> addProductQuantity(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype,@PathVariable("id") Integer productId,@PathVariable("quantity") Integer quantity) {

		if (tokenService.validateToken(token) && "seller".equalsIgnoreCase(usertype)) {
			Product prod = pService.getProductById(productId);
			prod.setQuantity(prod.getQuantity()+quantity);
			if(prod.getQuantity()>0) {
				prod.setStatus(ProductStatus.AVAILABLE);
			}
			pService.updateProduct(prod);
			return new ResponseEntity<Integer>(prod.getQuantity(), HttpStatus.OK);
		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}
	}
	
	@PutMapping("/reduce/quantity/{id}/{quantity}")
	public ResponseEntity<?> reduceProductQuantity(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype,@PathVariable("id") Integer productId,@PathVariable("quantity") Integer quantity) {

		if (tokenService.validateToken(token) && "customer".equalsIgnoreCase(usertype)) {
			Product prod = pService.getProductById(productId);
			
			if(quantity<=prod.getQuantity()) {
				
				prod.setQuantity(prod.getQuantity()-quantity);
				if(prod.getQuantity()<=0) {
					prod.setStatus(ProductStatus.OUTOFSTOCK);
				}
				pService.updateProduct(prod);
				return new ResponseEntity<Integer>(prod.getQuantity(), HttpStatus.OK);
			}else {
				return ResponseEntity.status(500).body("Product Quantity Greater than Available Stock");
			}
		} else {
			return ResponseEntity.status(401).body("Invalid Details!");
		}
	}
	
	

	// This method gets the product which needs to be added to the cart returns
	// product
    /*
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductFromCatalogByIdHandler(@PathVariable("id") Integer id) {

		Product prod = pService.getProductFromCatalogById(id);

		return new ResponseEntity<Product>(prod, HttpStatus.FOUND);

	}

	// This method will delete the product from catalog and returns the response
	// This will be called only when the product qty will be zero or seller wants to
	// delete for any other reason

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProductFromCatalogHandler(@PathVariable("id") Integer id) {
		
		String res = pService.deleteProductFromCatalog(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@PutMapping("/products")
	public ResponseEntity<Product> updateProductInCatalogHandler(@Valid @RequestBody Product prod) {

		Product prod1 = pService.updateProductIncatalog(prod);

		return new ResponseEntity<Product>(prod1, HttpStatus.OK);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductsHandler() {

		List<Product> list = pService.getAllProductsIncatalog();

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
  //this method gets the products mapped to a particular seller
	@GetMapping("/products/seller/{id}")
	public ResponseEntity<List<Product>> getAllProductsOfSellerHandler(@PathVariable("id") Integer id) {

		List<Product> list = pService.getAllProductsOfSeller(id);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@GetMapping("/products/{catenum}")
	public ResponseEntity<List<Product>> getAllProductsInCategory(@PathVariable("catenum") String catenum) {
		CategoryEnum ce = CategoryEnum.valueOf(catenum.toUpperCase());
		List<Product> list = pService.getProductsOfCategory(ce);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}

	@GetMapping("/products/status/{status}")
	public ResponseEntity<List<Product>> getProductsWithStatusHandler(@PathVariable("status") String status) {

		ProductStatus ps = ProductStatus.valueOf(status.toUpperCase());
		List<Product> list = pService.getProductsOfStatus(ps);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateQuantityOfProduct(@PathVariable("id") Integer id,@RequestBody Product prodDto){
		
		 Product prod =   pService.updateProductQuantityWithId(id, prodDto);
		
		 return new ResponseEntity<Product>(prod,HttpStatus.ACCEPTED);
	}
*/
}
