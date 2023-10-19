package com.ken.handmadeJewelry.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ken.handmadeJewelry.model.DbSelectAndInsert;

/**
 * Login controller
 * @author ken
 *
 */
@Controller
@CrossOrigin(origins = "*")
public class LoginController {
	
	private static final String LOGIN_SUCCESS_PAGE = "http://localhost:3000/";
	private static final String LOGIN_FAIL_PAGE = "http://localhost:3000/?error";
	
	@Autowired
	DbSelectAndInsert dbSearch;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	String login() {
		return "login";
	}

	//	Seems unnecessary for now
	//	List<ArtistEntity> loginError() {
	//		List<ArtistEntity> artistEntityList = new ArrayList<>();
	//		artistEntityList.add(new ArtistEntity());
	//
	//		return artistEntityList;
	//	}
	
	/**
	 * Redirect to login page (error)
	 * @return
	 */
	@GetMapping("/loginFail")
	public RedirectView loginError(){
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(LOGIN_FAIL_PAGE);

		return redirectView;
	}

	/**
	 * User resistration
	 * @param username
	 * @param password
	 */
	@GetMapping("/resister")
	void resister(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
		dbSearch.custResiter(username, passwordEncoder.encode(password));
		dbSearch.custRoleResiter(username);
	}

	/**
	 * Redirect to login page
	 * @return
	 */
	@GetMapping("/redirectLogin")
	public RedirectView redirectLogin(){
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(LOGIN_SUCCESS_PAGE);
		return redirectView;
	}

	/**
	 * Redirect to logout page
	 * @return
	 */
	@GetMapping("/redirectLogout")
	public RedirectView redirectLogout(){
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(LOGIN_SUCCESS_PAGE);
		return redirectView;
	}
}