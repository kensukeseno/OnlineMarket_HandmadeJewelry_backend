package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Users mapper
 * @author ken
 *
 */
@Mapper
public interface UsersMapper {

	/**
	 * Resister a new user
	 * @param username
	 * @param password
	 */
	@Insert("insert into users (username,password,enabled) values(#{username},#{password},'t')")
	void custResister(@Param("username") String username, @Param("password") String password);
}
