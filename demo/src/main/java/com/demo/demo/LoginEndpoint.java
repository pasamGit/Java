package com.demo.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginEndpoint {

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		Map<String, String> response = new HashMap<String, String>();
		if (user != null) {
			if (user.getEmail().equalsIgnoreCase("pasam.svr@gmail.com") && user.getPassword().equalsIgnoreCase("pasam")) {
				response.put("msg", "Login Success");

				return ResponseEntity.ok(response);
			} else {
				response.put("msg", "Login Failed");
				response.put("reason", "Invalid Credintials");
				return ResponseEntity.ok(response);
			}
		}
		response.put("msg", "Login Failed");
		response.put("reason", "bad request");
		return ResponseEntity.ok(response);
	}

}
