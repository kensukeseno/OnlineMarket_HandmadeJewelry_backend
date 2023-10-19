package com.ken.handmadeJewelry.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ken.handmadeJewelry.dto.ArtistWithProductList;
import com.ken.handmadeJewelry.entity.ProductEntity;
import com.ken.handmadeJewelry.mapper.ArtistMapper;
import com.ken.handmadeJewelry.mapper.AuthoritiesMapper;
import com.ken.handmadeJewelry.mapper.PastProductMapper;
import com.ken.handmadeJewelry.mapper.ProductMapper;
import com.ken.handmadeJewelry.mapper.UsersMapper;

/**
 * Get and insert data from or to database
 * @author ken
 *
 */
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

    /**
     * Get all product data
     * @return
     */
    public ProductEntity[] getProduct() {
        return productMapper.findAll();
    }
    
    /**
     * Get all product data corresponding to a specific product
     * @param product
     * @return
     */
    public ProductEntity[] getOneProduct(String product) {
        return productMapper.findOneProduct(product);
    }

    /**
     * Get all product data of a specific artist
     * @param artistId
     * @return
     */
    public ProductEntity[] getProductByArtist(Integer artistId) {
        return productMapper.findProductByArtist(artistId);
    }

    /**
     * Resister a new user
     * @param username
     * @param password
     */
    public void custResiter(String username, String password) {
        usersMapper.custResister(username, password);
    }

    /**
     * Resister a new user role
     * @param username
     */
    public void custRoleResiter(String username) {
        authoritiesMapper.custResister(username);
    }

    /**
     * Get all data of artists currently selling products
     * @return
     */
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

    /**
     * Resister a product
     * @param product
     * @param ammount
     * @param condition
     * @param photoAddress
     * @param price
     */
    public void resisterProduct(String product, Integer ammount, String condition, String photoAddress,
            Integer price) {
        productMapper.resisterProduct(product, ammount, photoAddress, price);
    }

    /**
     * Purchace a product
     * @param productId
     * @param ammount
     * @param buyerId
     */
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
