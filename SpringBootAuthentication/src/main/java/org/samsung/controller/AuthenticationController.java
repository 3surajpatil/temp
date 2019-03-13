package org.samsung.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.samsung.dao.AuthenticationService;
import org.samsung.model.UserCreds;
import org.samsung.model.UserToken;
import org.samsung.util.Constants;
import org.samsung.util.TokenGenerator;
import org.samsung.util.validator.UserCredsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@Autowired
	UserToken userToken;
	
	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserCredsValidator userCredsValidator;

	Logger logger = (Logger) LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultMethod() {
		logger.debug("default method Executed...!");
		return "Welcome...!";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserToken validateUser(@RequestParam String username, @RequestParam String password,
			HttpServletRequest req, HttpServletResponse res) {
		String token = null;
		
		// receiving email as username. 
		logger.debug("username received in validateUser:- " + username + " password received :-" + password);
		

		if (authenticationService.validateCredentials(username, password)) {
		
			userToken.setToken(TokenGenerator.generateToken());			
		logger.debug("userToken successful scenario:- "+userToken.toString());			
			// setting token and username to headers.			
			/*return ResponseEntity.status(HttpStatus.OK)
					.header(Constants.TOKEN_HEADER_KEY, TokenGenerator.generateToken())
					.header(Constants.USERNAME, username)
					.header(Constants.USER_ROLE_HEADER_KEY,"admin")  //need to put value from db.
					.body(Constants.REQUEST_SUCCESS_MESSAGE);*/
		
		return userToken;
		}

		//logger.debug("returning message from validateUser :- " + Constants.REQUEST_FAILURE_MESSAGE);		

		//return ResponseEntity.status(HttpStatus.OK).body(Constants.REQUEST_FAILURE_MESSAGE);
		logger.debug("userToken failure scenario:- "+userToken.toString());
		return userToken;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestParam String username, @RequestParam String password,@RequestParam String role) {

		logger.debug("username received registerUser :- " + username + " password received :-" + password);

		if (authenticationService.registerUser(username, password,role)) {
			return Constants.REQUEST_SUCCESS_MESSAGE;
		}
		
		return Constants.REQUEST_FAILURE_MESSAGE;
	}

}
