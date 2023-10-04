package com.ken.handmadeJewelry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ken.handmadeJewelry.model.DbSelectAndInsert;

@RestController
@CrossOrigin(origins = "*")
class PaymentController {

	@Autowired
	private DbSelectAndInsert dbSearch;

	@RequestMapping(value = "/buy")
	@ResponseBody
	public void payment(@RequestParam(name="productId") Integer productId, @RequestParam(name="ammount") Integer ammount,@RequestParam(name="buyerId") Integer buyerId){
		dbSearch.buyProduct(productId, ammount, buyerId);
	}
}
