package com.ken.handmadeJewelry.entity;

import lombok.Data;

@Data
public class ProductEntity {

	String product;
	Integer ammount;
	byte[] photo;
	Integer productId;
	Integer price;
	Integer artistId;
}
