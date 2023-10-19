package com.ken.handmadeJewelry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ken.handmadeJewelry.dto.ArtistWithProductList;
import com.ken.handmadeJewelry.entity.ProductEntity;
import com.ken.handmadeJewelry.model.DbSelectAndInsert;


/**
 * SearchController
 * @author ken
 *
 */
@RestController
@CrossOrigin(origins = "*")
class SearchController {

	@Autowired
	private DbSelectAndInsert dbSearch;

	/**
	 * Get products by name
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product")
	@ResponseBody
	public ProductEntity[] product(@RequestParam(name="product") String product){
		ProductEntity[] productList = dbSearch.getOneProduct(product);

		return productList;
	}

	/**
	 * Get products by artist name
	 * @return
	 */
	@RequestMapping(value = "/productByArtist")
	@ResponseBody
	public List<ArtistWithProductList> productByArtist(){
		List<ArtistWithProductList> ArtistWithProductListList =dbSearch.getArtistsWithProduct();

		return ArtistWithProductListList;
	}
}