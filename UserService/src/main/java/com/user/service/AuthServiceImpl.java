package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.dto.AuthRequest;
import com.user.dto.ResponseDto;
import com.user.entity.Admin;
import com.user.entity.User;
import com.user.repository.AdminRepository;
import com.user.repository.UserRepository;

import jakarta.validation.Valid;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private AdminRepository adminRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    
    @Override
    public User saveUser(User credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setRole("USER");
        repository.save(credential);
        return credential;
    }
    
    
    @Override
    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ADMIN");
        adminRepo.save(admin);
        return admin;
    }

    
    @Override
    public String generateToken(String username,String role) {
        return jwtService.generateToken(username,role);
    }

    
    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
    
    
    @Override
    public User getUserById(int id) {
    	return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }
    
    
    @Override
    public User updateUser(User user) {
    	User user1=repository.findByUsername(user.getUsername()).orElseThrow(()-> new RuntimeException("User not found with given Username"));
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setPhoneNo(user.getPhoneNo());
        repository.save(user1);
        return user1;
    }
	
	
	@Override
	 public ResponseDto getUserToken(AuthRequest authRequest) {
	    	System.out.println("yes .."+authRequest.getUsername()+"  "+authRequest.getPassword());
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        System.out.println(authenticate.isAuthenticated());
	        if (authenticate.isAuthenticated()) {
	        	User user=
	                	repository.findByUsername(authRequest.getUsername()).get();
	        	String token=
	        	//service.generateToken(authRequest.getUsername(),user.getRole());
	        		generateToken(user.getId()+"",user.getRole());
	        	
	        	ResponseDto resDto=new ResponseDto();
	        	resDto.setToken(token);
	        	resDto.setRole(user.getRole());
	        	return resDto;
	        } else {
	            throw new RuntimeException("invalid access");
	        }
	    }


	@Override
	 public ResponseDto getAdminToken(AuthRequest authRequest) {
	    	System.out.println("yes .."+authRequest.getUsername()+"  "+authRequest.getPassword());
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        System.out.println(authenticate.isAuthenticated());
	        if (authenticate.isAuthenticated()) {
	        	Admin admin=
	                	adminRepo.findByUsername(authRequest.getUsername()).get();
	        	String token=
	        	//service.generateToken(authRequest.getUsername(),user.getRole());
	        		generateToken(admin.getId()+"",admin.getRole());
	        	
	        	ResponseDto resDto=new ResponseDto();
	        	resDto.setToken(token);
	        	resDto.setRole(admin.getRole());
	        	return resDto;
	        } else {
	            throw new RuntimeException("invalid access");
	        }
	    }

}
