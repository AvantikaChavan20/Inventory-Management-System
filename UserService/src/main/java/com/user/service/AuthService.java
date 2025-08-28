package com.user.service;

import com.user.dto.AuthRequest;
import com.user.dto.ResponseDto;
import com.user.entity.Admin;
import com.user.entity.User;

public interface AuthService {
	
	User saveUser(User credential);
	Admin saveAdmin(Admin admin);
	String generateToken(String username,String role);
	void validateToken(String token);
	User getUserById(int id);
	User updateUser(User user);
	ResponseDto getUserToken(AuthRequest authRequest);
	ResponseDto getAdminToken(AuthRequest authRequest);
	
}
