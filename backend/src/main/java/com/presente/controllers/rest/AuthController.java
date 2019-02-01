package com.presente.controllers.rest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presente.dto.EmailDTO;
import com.presente.security.UserAuth;
import com.presente.services.AuthService;
import com.presente.services.JWTService;
import com.presente.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
	
	@Autowired
	private JWTService jwtUtil;
	@Autowired
	private AuthService authService;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserAuth user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		this.authService.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
