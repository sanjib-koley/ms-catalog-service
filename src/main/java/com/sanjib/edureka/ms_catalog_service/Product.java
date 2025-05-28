package com.sanjib.edureka.ms_catalog_service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable=false)
	private Integer productId;

	@NotNull
	@Size(min = 3, max = 100, message = "Product name size should be between 3-100")
	@Column(name = "product_name", nullable = false, length = 100)
	private String productName;

	@NotNull
	@DecimalMin(value = "0.00")
	@Column(name = "price", nullable=false)
	private Double price;

	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	
	@Min(value = 0)
	@Column(name = "quantity", nullable = true)
	private Integer quantity=0;

	@Enumerated(EnumType.STRING)
	private CategoryEnum category;

	@Enumerated(EnumType.STRING)
	private ProductStatus status = ProductStatus.OUTOFSTOCK;

	@Column(name = "seller_id", nullable = false)
	private Long sellerId;

}
