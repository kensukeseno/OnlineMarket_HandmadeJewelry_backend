package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ken.handmadeJewelry.entity.ArtistEntity;

@Mapper
public interface ArtistMapper {

//	全選択のselect文の作成
	ArtistEntity[] findAll();

//	アーティスト名に一致するアーティスト情報の取得
	ArtistEntity findByArtistId(@Param("artistId") Integer artistId);
}
