package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ken.handmadeJewelry.entity.ArtistEntity;

/**
 * Artist mapper
 * @author ken
 *
 */
@Mapper
public interface ArtistMapper {

	/**
	 * Get all records from artist table
	 * @return
	 */
	ArtistEntity[] findAll();

	/**
	 * Get records corresponding to param from artist table
	 * @param artistId
	 * @return
	 */
	ArtistEntity findByArtistId(@Param("artistId") Integer artistId);
}
