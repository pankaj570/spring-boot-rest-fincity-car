package com.fincity.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fincity/api/v1/")
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Map<String, String> getResource() {
		Map<String, String> resource = new HashMap<String, String>();
		resource.put("x-auth-token", "session created successfully and you get x-auth-token in header response.");
		return resource;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Map<String, String> logout(HttpSession session) {
		session.invalidate();
		Map<String, String> resource = new HashMap<String, String>();
		resource.put("message", "user logout successfully");
		return resource;
	}
}
