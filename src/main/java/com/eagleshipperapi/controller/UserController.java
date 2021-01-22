package com.eagleshipperapi.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.bean.Token;
import com.eagleshipperapi.bean.User;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.eagleshipperapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	//create user 
	@PostMapping("/")
	public ResponseEntity<User> createNewUser(@RequestParam("file") MultipartFile file,
			@RequestParam("userId")String userId,
			@RequestParam("name") String name,
			@RequestParam("address")String address,
			@RequestParam("contactNumber") String contactNumber,
			@RequestParam("token") String token) throws IOException, ResourceNotFoundException{
		if(file.isEmpty())
			throw new ResourceNotFoundException("image not found.");
		 User user=userService.createUser(file,new User(userId,name,address,contactNumber,"",token));
		 return new ResponseEntity<User>(user,org.springframework.http.HttpStatus.OK);
	}
	
	
	//get All User
	@GetMapping("/")
	public ResponseEntity<ArrayList<User>> getUsers() throws InterruptedException, ExecutionException{
		ArrayList<User>al = userService.getUser();
		return new ResponseEntity<ArrayList<User>>(al,org.springframework.http.HttpStatus.OK);
	}
	
	//get Single User by ID
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) throws InterruptedException, ExecutionException, ResourceNotFoundException{
		User user = userService.getUser(id);
		if(user==null)
			 throw new ResourceNotFoundException("User not Found");
		return new ResponseEntity<User>(user,org.springframework.http.HttpStatus.OK);
	}
	
	//delete Single User by ID
	@DeleteMapping("/{id}")
		public ResponseEntity<User> deleteUser(@PathVariable String id) throws InterruptedException, ExecutionException, ResourceNotFoundException{
			User user = userService.deleteUser(id);
			if(user==null)
				 throw new ResourceNotFoundException("User not Found");
			return new ResponseEntity<User>(user,org.springframework.http.HttpStatus.OK);
		}
	
	//update without image
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws InterruptedException, ExecutionException, ResourceNotFoundException{
		User u = userService.updateUser(user);
		if(u == null)
			throw new ResourceNotFoundException("User not Found");
		return new ResponseEntity<User>(u,org.springframework.http.HttpStatus.OK);
	}
	
	//update  image
		@PostMapping("/update/image")
		public ResponseEntity<User> updateUser(@RequestParam("file")MultipartFile file,@RequestParam("userId") String userId) throws InterruptedException, ExecutionException, ResourceNotFoundException, IOException{
			User u = userService.updateUserById(file, userId);
			if(u == null)
				throw new ResourceNotFoundException("User not Found");
			return new ResponseEntity<User>(u,org.springframework.http.HttpStatus.OK);
		}
	
	
	//get Token Of All Transporter
		@GetMapping("/token")
		public ResponseEntity<ArrayList<Token>> getTokenTransporters() throws InterruptedException, ExecutionException, IOException, ResourceNotFoundException{
			
			ArrayList<Token> al = userService.getTokens();
			if(al.size() != 0)
				return new ResponseEntity<>(al, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Transporter Found");
		}
		
}
