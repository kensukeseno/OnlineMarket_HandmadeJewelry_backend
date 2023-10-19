package com.ken.handmadeJewelry.entity;

import lombok.Data;

/**
 * PastProduct entity
 * @author ken
 *
 */
@Data
public class PastProductEntity {

	String product;
	Integer ammount;
	String photo;
	Integer productId;
	Integer price;
	Integer artistId;
	Integer buyerId;
}
