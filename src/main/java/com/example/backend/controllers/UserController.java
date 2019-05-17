package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.utilities.Helper;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> index() {

		List<User> users = userRepository.findAll();

		return Helper.createResponse(true, users, "success", HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> store(@ModelAttribute User user) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);

		return Helper.createResponse(true, newUser, "success", HttpStatus.CREATED);
	}

}
