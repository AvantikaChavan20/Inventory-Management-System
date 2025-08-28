package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user found"));
	}

	@Override
	public User updateUser(Integer id, User userDetails) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User updatedUser=userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user found"));
		updatedUser.setEmail(userDetails.getEmail());
		updatedUser.setPhoneNo(userDetails.getPhoneNo());
		return userRepo.save(updatedUser);
	}

	@Override
	public String deleteUser(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		return "User deleted successfully";
	}
	
	

}
