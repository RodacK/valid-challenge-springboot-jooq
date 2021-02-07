package com.valid.challenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valid.challenge.jooq.model.tables.pojos.User;
import com.valid.challenge.model.ProcessRequest;

@RestController
@RequestMapping("valid/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers() {
		try {
			return ResponseEntity.ok().body(repository.getUsers());
		}catch (Exception e) {
			return builError(e);
		}		
	}
	
	
	@PostMapping(path = "/process", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processUsers(@Valid @RequestBody ProcessRequest users) {
		try {
			repository.processUser(users);	
			return ResponseEntity.ok("{\"message\": \"se han procesado\"}");
		}catch (Exception e) {
			return builError(e);
		}		
	}
	
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		try {
			repository.saveUser(user);
			return ResponseEntity.ok("{\"message\": \"se ha guardado\"}");
		}catch (Exception e) {
			return builError(e);
		}		
	}
	
	public ResponseEntity<?> builError(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \""+e.getMessage()+"\"}");
	}
}
