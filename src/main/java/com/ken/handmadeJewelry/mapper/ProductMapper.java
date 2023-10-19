package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ken.handmadeJewelry.entity.ProductEntity;

/**
 * Product mapper
 * @author ken
 *
 */
@Mapper
public interface ProductMapper {

	/**
	 * Get all records from product table
	 * @return
	 */
	ProductEntity[] findAll();

	/**
	 * Get records corresponding to artist id from product table
	 * @param artistId
	 * @return
	 */
	ProductEntity[] findProductByArtist(@Param("artistId") Integer artistId);

	/**
	 * Get records corresponding to product param from product table
	 * @param product
	 * @return
	 */
	ProductEntity[] findOneProduct(@Param("product") String product);

	/**
	 * Get one record corresponding to product id from product table
	 * @param productId
	 * @return
	 */
	ProductEntity findProduct(@Param("productId") Integer productId);

	/**
	 * Resister a product to product table
	 * @param product
	 * @param ammount
	 * @param photoAddress
	 * @param price
	 */
	@Insert("insert into product (product, ammount, photo, product_id, price) values(#{artist}, #{product}, #{ammount},#{photoAddress},nextval('product_id_seq'), #{price});")
	void resisterProduct(@Param("product") String product, @Param("ammount") Integer ammount, @Param("photoAddress") String photoAddress, @Param("price") Integer price);

	/**
	 * Delete a product corresponding to product id from product table
	 * @param id
	 */
	@Delete("delete from product where product_id = #{id}")
	void deleteProduct(@Param("id") Integer id);

	/**
	 * Change the number of a product
	 * @param id
	 * @param ammount
	 */
	@Update("update product set ammount=#{ammount} where product_id = #{id}")
	void alterProductAmmount(@Param("id") Integer id, @Param("ammount") Integer ammount);
}
