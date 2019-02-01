package com.presente.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.presente.security.UserAuth;

@Service
public class UserService {
	
	public static UserAuth authenticated() {
		try {
			return (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
