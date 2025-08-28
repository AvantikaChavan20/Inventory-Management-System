package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.AuthRequest;
import com.user.dto.ResponseDto;
import com.user.entity.Admin;
import com.user.entity.User;
import com.user.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
		
    @Autowired
    private AuthService service;
    
    //To test that the connection between gatway, eureka server and microservice  
    @GetMapping("/test")
    public ResponseEntity<String> test(){
    	return new ResponseEntity<String>(" User Service is runing successfully", HttpStatus.OK);
    }
    
    
    //To find the user by UserId
    @GetMapping("/mydetails")
    public ResponseEntity<User> findUserById(@RequestHeader("loggedInUser") int id){
    	return new ResponseEntity<User>(service.getUserById(id),HttpStatus.ACCEPTED);
    }

    
    //To Register the user to database
    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody @Valid User user) {
        return new ResponseEntity<User>(service.saveUser(user),HttpStatus.OK);
    }

    
    //To generate the token and pass it to the client
    @PostMapping("/userLogin")
    public ResponseDto getToken(@RequestBody @Valid AuthRequest authRequest) {
		return service.getUserToken(authRequest);
    }
    

    //To validate the token given by the user
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        service.validateToken(token);
        return new ResponseEntity<String> ("Token is valid", HttpStatus.OK);
    }
    
    
  //To Register the admin to database
    @PostMapping("/admin/register")
    public ResponseEntity<Admin> addNewadmin(@RequestBody @Valid Admin admin) {
        return new ResponseEntity<Admin>(service.saveAdmin(admin), HttpStatus.OK);
    }
    
    
    @PostMapping("/adminLogin")
    public ResponseDto getAdminToken(@RequestBody @Valid AuthRequest authRequest) {
		return service.getAdminToken(authRequest);
    }
    
}
