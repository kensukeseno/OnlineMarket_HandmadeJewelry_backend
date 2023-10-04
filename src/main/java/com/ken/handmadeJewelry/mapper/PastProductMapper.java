package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PastProductMapper {

//	購入した商品の登録
	@Insert("insert into pastProduct (artist_id, product, ammount, photo, product_id, price, buyer_id) values(#{artistId}, #{product}, #{ammount},#{photo},#{product_id}, #{price}, #{buyerId});")
	void resisterProduct(@Param("artistId") Integer artistId, @Param("product") String product, @Param("ammount") Integer ammount, @Param("photo") byte[] photo, @Param("product_id") Integer product_id, @Param("price") Integer price, @Param("buyerId") Integer buyerId);
}