package com.ken.handmadeJewelry.entity;

import lombok.Data;

/**
 * Users entity
 * @author ken
 *
 */
@Data
public class UsersEntity {

	String  username;
	String  password;
	String  enabled;
	Integer userId;
}
