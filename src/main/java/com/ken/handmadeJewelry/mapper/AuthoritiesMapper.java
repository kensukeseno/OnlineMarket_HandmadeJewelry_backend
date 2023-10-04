package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthoritiesMapper {

//	新規ユーザーの登録
	@Insert("insert into authorities (username,authority) values(#{username},'ROLE_USER')")
	void custResister(@Param("username") String username);
}
