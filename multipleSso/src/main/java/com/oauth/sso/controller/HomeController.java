package com.oauth.sso.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String loginpage() {
		return "loginmain";

	}

	@GetMapping("/success")
	public String loginSuccess(Model model, @AuthenticationPrincipal OAuth2User principal) {
		model.addAttribute("name", principal.getAttribute("name"));
		return "loginsuccess";
	}

	@GetMapping("/error")
	public String error(@AuthenticationPrincipal OAuth2User principal) {
		return "Error Occured";
	}

//	@RequestMapping("/loginsuccess")
//	public String homepage() {
//
//		return "loginsuccess";
//	}
//	@RequestMapping("/success")
//	public String success(Model model, @AuthenticationPrincipal OAuth2User principal) {
//		
//		model.addAttribute("name").addAttribute(principal.getAttribute("name"));
//		return "loginsuccess";
//	}
}
