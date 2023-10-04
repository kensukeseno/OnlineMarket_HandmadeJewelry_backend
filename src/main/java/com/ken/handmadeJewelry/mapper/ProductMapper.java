package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ken.handmadeJewelry.entity.ProductEntity;

@Mapper
public interface ProductMapper {

//	全選択のselect文の作成
	ProductEntity[] findAll();

//	特定のアーティストの商品情報の取得
	ProductEntity[] findProductByArtist(@Param("artistId") Integer artistId);

//	特定の種類の商品情報の取得
	ProductEntity[] findOneProduct(@Param("product") String product);

//	特定の商品情報の取得
	ProductEntity findProduct(@Param("productId") Integer productId);

//	商品の登録
	@Insert("insert into product (product, ammount, photo, product_id, price) values(#{artist}, #{product}, #{ammount},#{photoAddress},nextval('product_id_seq'), #{price});")
	void resisterProduct(@Param("product") String product, @Param("ammount") Integer ammount, @Param("photoAddress") String photoAddress, @Param("price") Integer price);

//	商品の削除
	@Delete("delete from product where product_id = #{id}")
	void deleteProduct(@Param("id") Integer id);

//	商品数の変更
	@Update("update product set ammount=#{ammount} where product_id = #{id}")
	void alterProductAmmount(@Param("id") Integer id, @Param("ammount") Integer ammount);
}
