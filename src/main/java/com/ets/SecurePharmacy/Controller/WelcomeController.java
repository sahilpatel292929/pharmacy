package com.ets.SecurePharmacy.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.tenant.model.JwtRequest;
import com.ets.SecurePharmacy.tenant.model.JwtResponse;
import com.ets.SecurePharmacy.serviceimpl.JwtService;
import com.ets.SecurePharmacy.serviceimpl.UserService;

@RestController
public class WelcomeController {

//	@Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@PostConstruct
	public void initRoleAndUser() {
		userService.initRoleAndUser();
	}

//    
	@Autowired
	private JwtService jwtService;

	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}

	@GetMapping({ "/forAdmin" })
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL is only accessible to the admin";
	}

	@GetMapping({ "/forUser" })
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String forUser() {
		return "This URL is only accessible to the user";
	}
}
