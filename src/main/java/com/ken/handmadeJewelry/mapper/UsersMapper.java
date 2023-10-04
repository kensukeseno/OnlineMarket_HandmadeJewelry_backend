package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {

//	新規ユーザーの登録
	@Insert("insert into users (username,password,enabled) values(#{username},#{password},'t')")
	void custResister(@Param("username") String username, @Param("password") String password);
}
