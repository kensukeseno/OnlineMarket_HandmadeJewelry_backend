package com.ken.handmadeJewelry.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ken.handmadeJewelry.dto.ArtistWithProductList;
import com.ken.handmadeJewelry.entity.ArtistEntity;
import com.ken.handmadeJewelry.entity.ProductEntity;
import com.ken.handmadeJewelry.model.DbSelectAndInsert;


@RestController
@CrossOrigin(origins = "*")
class restTest {

	@Autowired
	private DbSelectAndInsert dbSearch;

	@RequestMapping(value = "/product")
	@ResponseBody
	public ProductEntity[] product(@RequestParam(name="product") String product){

//		販売中商品情報の取得＋セッション情報登録
		ProductEntity[] productList = dbSearch.getOneProduct(product);

//		System.out.println(productList[0]);

		return productList;
	}

	@RequestMapping(value = "/productByArtist")
	@ResponseBody
	public List<ArtistWithProductList> productByArtist(){

		List<ArtistWithProductList> ArtistWithProductListList =dbSearch.getArtistsWithProduct();

		return ArtistWithProductListList;
	}
}