package org.samsung.dao;

import java.util.HashMap;
import java.util.Map;

import org.samsung.dao.rowmappers.UserProfileRowMapper;
import org.samsung.model.UserCreds;
import org.samsung.model.UserProfile;
import org.samsung.model.UserToken;
import org.samsung.util.Constants;
import org.samsung.util.EncoderDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationService {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserToken userToken;
	
	private Logger logger=LoggerFactory.getLogger(AuthenticationService.class);
	
	  public boolean validateCredentials(String email, String password)
	  {
		    // receiving email as username.
			UserProfile  userProfile=(UserProfile) jdbcTemplate.queryForObject(Constants.SELECT_USER_SQL,new Object[] {email},new UserProfileRowMapper());
			
			logger.debug("validateCredentials :- username:- "+userProfile.getEmail_Id()+" password :-"+userProfile.getPassword()+" role:-"+userProfile.getAccess_Type());
			
				if( userProfile!=null && userProfile.getPassword()!=null && userProfile.getPassword()!="")
				{		
					
					//for local testing encoding password with base64
					String base64EncodedPassword=EncoderDecoder.encodePassword(password);
					
					// appending constant to base64encoded password.					
					String base64EncodedPasswordWithConstant=base64EncodedPassword+Constants.APPENDER;
					logger.debug("base64EncodedPasswordWithConstant :- "+base64EncodedPasswordWithConstant);
					
					if( BCrypt.checkpw( base64EncodedPasswordWithConstant, userProfile.getPassword()) )
					{
						logger.debug("validateCredentials :- Valid Credentials entered...!");
						userToken.setRole(userProfile.getAccess_Type());
						userToken.setUsername(userProfile.getEmail_Id());
						return true;
					}
				}															
			logger.debug("AuthenticationService:validateCredentials :- Invalid Credentials entered...!");
				
		  return false;
	  }	 
		  
	  // Not in use
	  public boolean registerUser(String userName, String password,String role)
	  {
		  boolean registrationStatus=false;
		  
		  logger.debug("in AuthenticationServie registerUser():- userName:= "+userName+" password:- "+password); 
		  			  				  	
				
		 Map<String, Object> parameters = new HashMap<String, Object>();		   
		    parameters.put("first_name", "dummy_name"); // this dummy name should be received in request param.
		    parameters.put("Email_Id", userName);
		    parameters.put("password", getBcryptedPassword(password));
		    parameters.put("access_type", role); // for now role should be 'admin'/'test'/'user'.
		    parameters.put("Pwd_Change_Days_Cnt", 0);  // setting default value 0
		 
		  // Preparing SimipleJdbcInsert object for 'user_profile' table.		    
		 SimpleJdbcInsert sji=new SimpleJdbcInsert(jdbcTemplate)
					.withTableName("user_profile")
					.usingGeneratedKeyColumns("User_Code");    
		
		Number generatedUserCode=sji.executeAndReturnKey(parameters);
		
		if(generatedUserCode.longValue()>=1001)
		{
			registrationStatus=true;
		}
		
		logger.debug("in AuthenticationServie registerUser() registrationStatus is :-"+registrationStatus+" generatedUserCode:- "+generatedUserCode );
		  return registrationStatus; 
	  }
	  
	  public String getBcryptedPassword(String password){
		  
		  //encoding with Base64 first . at at time of retrival we will get BASE64 encrypted password...!
		  //dont forget to remove before delivery as
		  //password will be bydefault base64 coming tho this rest api
		  
		  String base64EncodedPassword=EncoderDecoder.encodePassword(password);
		  
		  // appending base64 password with extra secret key constant.
		  String base64EncodedPasswordAndConstant=base64EncodedPassword+Constants.APPENDER;
		  
		  //hashing with BCrypt
		  String bCryptEncodedPasword= BCrypt.hashpw(base64EncodedPasswordAndConstant, BCrypt.gensalt(12));

		  logger.debug("bCrypted Password:- "+bCryptEncodedPasword);
		  return bCryptEncodedPasword;
	  }
	  
}//AuthenticationService









