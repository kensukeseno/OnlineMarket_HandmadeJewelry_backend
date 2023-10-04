package com.ken.handmadeJewelry.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ken.handmadeJewelry.entity.ArtistEntity;
import com.ken.handmadeJewelry.model.DbSelectAndInsert;

@Controller
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	DbSelectAndInsert dbSearch;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    とりあえず必要なし
	@GetMapping("/login")
	String login() {
		return "login";
	}

	@GetMapping("/loginFail")
//	List<ArtistEntity> loginError() {
//		List<ArtistEntity> artistEntityList = new ArrayList<>();
//		artistEntityList.add(new ArtistEntity());
//
//		return artistEntityList;
//	}
	   public RedirectView loginError(){

	       RedirectView redirectView = new RedirectView();

	       redirectView.setUrl("http"
	       		+ "://localhost:3000/?error");

	       return redirectView;
	   }

	@GetMapping("/resister")
	void resister(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
		dbSearch.custResiter(username, passwordEncoder.encode(password));
		dbSearch.custRoleResiter(username);
	}

	@GetMapping("/redirectLogin")
	   public RedirectView redirectLogin(){

	       RedirectView redirectView = new RedirectView();
	       redirectView.setUrl("http"
	       		+ "://localhost:3000/");

	       return redirectView;
	   }

	@GetMapping("/redirectLogout")
	   public RedirectView redirectLogout(){

	       RedirectView redirectView = new RedirectView();
	       redirectView.setUrl("http"
	       		+ "://localhost:3000/");

	       return redirectView;
	   }
}