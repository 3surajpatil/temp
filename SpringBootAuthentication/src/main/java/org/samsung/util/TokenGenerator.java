package org.samsung.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;



public class TokenGenerator {

	private static Algorithm algorithm = Algorithm.HMAC256(Constants.TOKEN_SECRET);
	 private static String token;

	 private static Logger  logger=LoggerFactory.getLogger(TokenGenerator.class);
	 
	 public static String generateToken()
	{					
		Long currentTime=System.currentTimeMillis();
		Date currentDate=new Date(currentTime);
		Date expiryDate=new Date(currentTime+1000*30*60);

		logger.debug("current time :- "+currentDate.toString());
		logger.debug("token Expiry :- "+expiryDate.toString());

		try {

		    //Algorithm algorithm = Algorithm.HMAC256("secret");

		 token  = JWT.create()
		        .withIssuer(Constants.TOKEN_ISSUER)		    	
				.withIssuedAt(currentDate)
				.withExpiresAt(expiryDate)
		        .sign(algorithm);

		} catch (JWTCreationException exception){
		   exception.printStackTrace();
		}

		return token;
	}

	

	

	/*public static void main(String[] args) {
		
		TokenGenerator tokenGenerator=new TokenGenerator();		
		System.out.println("generate token is :- "+ tokenGenerator.generateToken());
		tokenGenerator.verifyToken();		

	}*/



}