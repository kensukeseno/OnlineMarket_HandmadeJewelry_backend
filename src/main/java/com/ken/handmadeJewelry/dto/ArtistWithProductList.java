package com.ken.handmadeJewelry.dto;

import com.ken.handmadeJewelry.entity.ArtistEntity;
import com.ken.handmadeJewelry.entity.ProductEntity;

import lombok.Data;

/**
 * DTO: artist with products list
 * @author ken
 *
 */
@Data
public class ArtistWithProductList {

	public ArtistEntity artist;
	public ProductEntity[] productEntityList;
}
