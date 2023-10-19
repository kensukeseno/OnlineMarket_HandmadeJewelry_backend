package com.ken.handmadeJewelry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Authorities mapper
 * @author ken
 *
 */
@Mapper
public interface AuthoritiesMapper {

	/**
	 * Resister a new user
	 * @param username
	 */
	@Insert("insert into authorities (username,authority) values(#{username},'ROLE_USER')")
	void custResister(@Param("username") String username);
}
