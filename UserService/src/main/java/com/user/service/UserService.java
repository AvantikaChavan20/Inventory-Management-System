package com.user.service;

import java.util.List;

import com.user.entity.User;
import com.user.exception.UserNotFoundException;

public interface UserService {
	public List<User> getAllUsers();
	public User getUserById(Integer id) throws UserNotFoundException;
	public User updateUser(Integer id, User userDetails) throws UserNotFoundException;
	public String deleteUser(Integer id) throws UserNotFoundException;
}
