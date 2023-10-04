package com.ken.handmadeJewelry.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ken.handmadeJewelry.dto.ArtistWithProductList;
import com.ken.handmadeJewelry.entity.ArtistEntity;
import com.ken.handmadeJewelry.entity.ProductEntity;
import com.ken.handmadeJewelry.mapper.ArtistMapper;
import com.ken.handmadeJewelry.mapper.AuthoritiesMapper;
import com.ken.handmadeJewelry.mapper.PastProductMapper;
import com.ken.handmadeJewelry.mapper.ProductMapper;
import com.ken.handmadeJewelry.mapper.UsersMapper;

@Service
public class DbSelectAndInsert {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AuthoritiesMapper authoritiesMapper;

    @Autowired
    private PastProductMapper pastProductMapper;

//	特定の種類の商品の全件検索結果を取得
    public ProductEntity[] getOneProduct(String product) {
        return productMapper.findOneProduct(product);
    }

//	ある作者の商品の全件検索結果を取得
    public ProductEntity[] getProductByArtist(Integer artistId) {
        return productMapper.findProductByArtist(artistId);
    }

//	商品の全件検索結果を取得
    public ProductEntity[] getProduct() {
        return productMapper.findAll();
    }

//	ユーザーの新規登録
    public void custResiter(String username, String password) {
        usersMapper.custResister(username, password);
    }

//	ユーザーロールの新規登録
    public void custRoleResiter(String username) {
        authoritiesMapper.custResister(username);
    }

//	商品を販売中のアーティストのリストを取得
    public List<ArtistWithProductList> getArtistsWithProduct() {
        ProductEntity[] products = getProduct();
        Set<Integer> artists = new HashSet<>();
        for(ProductEntity product: products) {
            artists.add(product.getArtistId());
        }

        List<ArtistWithProductList> artistWithProductListList = new ArrayList<>();

        for (Integer artist : artists) {
            ArtistWithProductList artistWithProductList = new ArtistWithProductList();
            artistWithProductList.setArtist(artistMapper.findByArtistId(artist));
            artistWithProductList.setProductEntityList(productMapper.findProductByArtist(artist));
            artistWithProductListList.add(artistWithProductList);
        }
        return artistWithProductListList;
    }

//	販売商品をを登録
    public void resisterProduct(String product, Integer ammount, String condition, String photoAddress,
            Integer price) {
        productMapper.resisterProduct(product, ammount, photoAddress, price);
    }

//	商品を購入
    public void buyProduct(Integer productId, Integer ammount,Integer buyerId) {
        ProductEntity productEntity = productMapper.findProduct(productId);
        int leftAmmount = productEntity.getAmmount();
        pastProductMapper.resisterProduct(productEntity.getArtistId(), productEntity.getProduct(), ammount, productEntity.getPhoto(), productEntity.getProductId(), productEntity.getPrice(), buyerId);

        if(leftAmmount == ammount) {
            productMapper.deleteProduct(productId);
        }else {
            productMapper.alterProductAmmount(productId, leftAmmount - ammount);
        }
    }
}
