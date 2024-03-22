package com.ken.handmadeJewelry.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ken.handmadeJewelry.model.DbSelectAndInsert;

/**
 * Login controller
 * @author ken
 *
 */
@Controller
@CrossOrigin(origins = "*")
public class LoginController {
		
	@Autowired
	DbSelectAndInsert dbSearch;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value ="/login")
	@ResponseBody
	public Map<String, String> login() {
		Map<String, String> loginResult = new HashMap<>();
		loginResult.put("result","success");
		return loginResult;
	}

	/**
	 * Redirect to login page (error)
	 * @return
	 */
	@RequestMapping(value = "/loginFail")
	@ResponseBody
	public Map<String, String> redirectError(){
		Map<String, String> loginResult = new HashMap<>();
		loginResult.put("result","fail");
		return loginResult;
	}

	/**
	 * User resistration
	 * @param username
	 * @param password
	 */
	@RequestMapping(value = "/resister")
	@ResponseBody
	void resister(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
		dbSearch.custResiter(username, passwordEncoder.encode(password));
		dbSearch.custRoleResiter(username);
	}

	/**
	 * Redirect to login page
	 * @return
	 */
	@RequestMapping(value = "/redirectLogin")
	@ResponseBody
	public Map<String, String> redirectLogin(){
		Map<String, String> loginResult = new HashMap<>();
		loginResult.put("result","success");
		return loginResult;
	}

	/**
	 * Redirect to logout page
	 * This method is probably unnecessary
	 * @return
	 */
	@RequestMapping(value = "/redirectLogout")
	@ResponseBody
	public void redirectLogout(){
	}
}