package org.samsung.service;

import java.util.Map;

import org.samsung.constants.Constants;
import org.samsung.model.UserToken;
import org.samsung.repository.UserTokensRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class RequestrValidationService {

	
	private static Algorithm algorithm = Algorithm.HMAC256(Constants.TOKEN_SECRET);
	Logger logger = LoggerFactory.getLogger(RequestrValidationService.class);

	@Autowired
	UserTokensRepositoryImpl userTokensRepository;

	public String verifyUserAndSotreToken(String userName, String password) {		
		
		UserToken userToken = verifyUser(userName, password);

		if (userToken != null && userToken.getUsername() != null && userToken.getToken() != null
				&& userToken.getRole() != null) {

			// as user is verified, storing token to radis.
			userTokensRepository.save(userToken);
			logger.debug("token stored successfully...!");

			return Constants.REQUEST_SUCCESS_MESSAGE;
		}

		return Constants.REQUEST_FAILURE_MESSAGE;
	}

	//for testing	
	public void deleteTokens(String userNameToken){
		
		userTokensRepository.deleteToken(userNameToken);
		
	}
		
	public boolean isValidRequest(String userName){
		boolean validReqeust=false;
		
		UserToken userToken=userTokensRepository.find(userName);		
		if(userToken!=null)
		{
			logger.debug(userToken.toString());
			if(verifyToken(userToken.getToken()))
			validReqeust=true;
		}				
		
		return validReqeust;
	}
	
	public boolean verifyToken(String token)
	{
		boolean isValidToken=false;
		try {
			
			//token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJpc3MiOiJoYXJtYW4iLCJleHAiOjE1Mzk3NjIxNDcsImlhdCI6MTUzOTc2MDM0N30.lrzSZWZ6ictTLK3Vv8kQYVRvycOnYsltCo54xOw_4DQ";
			
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(Constants.TOKEN_ISSUER)		        
		        .build(); 

		    DecodedJWT jwt = verifier.verify(token);
		    logger.debug("signature:- "+jwt.getSignature());
		    logger.debug("issuer:- "+jwt.getIssuer());
		    logger.debug("issued At:-"+jwt.getIssuedAt());
		    logger.debug("expires At :-"+jwt.getExpiresAt());
		    
		    isValidToken=true;
		    
		} catch (JWTVerificationException exception){			
			exception.getMessage();
		}				
		return isValidToken;
	}
	
	public UserToken verifyUser(String userName,String password)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// adding requestparameters.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add(Constants.REQUEST_PARAM_USERNAME, userName);
		params.add(Constants.REQUEST_PARAM_PASSWORD, password);

		// making http call.
		HttpEntity<MultiValueMap<String, String>> httpEntityRequest = new HttpEntity<MultiValueMap<String, String>>(
				params, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(Constants.AUTHENTICATION_URL, httpEntityRequest, String.class);

		// extracting header params from response.
		HttpHeaders httpHeaders = response.getHeaders();
		Map<String, String> headersMap = httpHeaders.toSingleValueMap();

		String token = headersMap.get(Constants.TOKEN_HEADER_KEY);
		String username = headersMap.get(Constants.REQUEST_PARAM_USERNAME);
		String role = headersMap.get(Constants.USER_ROLE_HEADER_KEY);

		UserToken userToken=new UserToken(username,token,role);
		logger.debug("received token :-" + userToken.toString());
		
		return userToken;
	}

	

}
