package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Past product mapper
 * @author ken
 *
 */
@Mapper
public interface PastProductMapper {

	/**
	 * Resister purchaced products to pastProduct table
	 * @param artistId
	 * @param product
	 * @param ammount
	 * @param photo
	 * @param product_id
	 * @param price
	 * @param buyerId
	 */
	@Insert("insert into pastProduct (artist_id, product, ammount, photo, product_id, price, buyer_id) values(#{artistId}, #{product}, #{ammount},#{photo},#{product_id}, #{price}, #{buyerId});")
	void resisterProduct(@Param("artistId") Integer artistId, @Param("product") String product, @Param("ammount") Integer ammount, @Param("photo") byte[] photo, @Param("product_id") Integer product_id, @Param("price") Integer price, @Param("buyerId") Integer buyerId);
}