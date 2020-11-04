package com.bezkoder.spring.jwt.mongodb.controllers;

import com.bezkoder.spring.jwt.mongodb.models.Transaction;
import com.bezkoder.spring.jwt.mongodb.payload.response.JwtResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.repository.TransactionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;


	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}


	@PostMapping(value = "/transactions", consumes = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> addTransaction(@RequestHeader("username") String username, @RequestBody Transaction transaction) {
		transactionRepository.save(transaction);
		return ResponseEntity.ok(new MessageResponse("Transaction registered successfully!"));
	}


	@GetMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Transaction> getTransactions(@RequestParam(value ="senderUsername", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") String senderUser,
			@RequestParam(value ="startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
												 @RequestParam(value ="endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return transactionRepository.findBySenderUserAndDateBetween(senderUser, startDate, endDate);
	}

	@DeleteMapping(value = "/account")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteAccount(@RequestHeader("username") String username) {
		userRepository.deleteByUsername(username);
		return ResponseEntity.ok(new MessageResponse("User was successfully deleted"));
	}

}
