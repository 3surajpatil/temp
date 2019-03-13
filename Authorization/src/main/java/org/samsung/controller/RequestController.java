package org.samsung.controller;

import javax.servlet.http.HttpServletRequest;

import org.samsung.constants.Constants;
import org.samsung.service.RequestrValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
	
	Logger logger=LoggerFactory.getLogger(RequestController.class);
	
	@Autowired
	RequestrValidationService requestrValidationService;	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return Constants.REQEUST_WELCOME_MESSAGE;
	}

	@RequestMapping(value="/", method = RequestMethod.POST)	
	public String validateRequest(@RequestParam String username){
	
	
		if(requestrValidationService.isValidRequest(username))
		{
			logger.debug("valid request...!");
			return Constants.REQUEST_SUCCESS_MESSAGE;
		}
		
		return Constants.REQUEST_FAILURE_MESSAGE;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		return requestrValidationService.verifyUserAndSotreToken(request.getParameter(Constants.REQUEST_PARAM_USERNAME), request.getParameter(Constants.REQUEST_PARAM_PASSWORD));
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.DELETE)
	public String delete_tokens(@RequestParam String username){
	
		logger.debug("username received for deleting is :-"+username);
		requestrValidationService.deleteTokens(username);
		
		return "tokens deleted successfully...!";
	}
	

}
