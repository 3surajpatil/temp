package org.samsung.repository;

import org.samsung.constants.Constants;
import org.samsung.model.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokensRepositoryImpl implements UserTokensRepository {

	Logger logger=LoggerFactory.getLogger(UserTokensRepositoryImpl.class);
	
	@Autowired
	private RedisTemplate<String,UserToken> redisTemplate;
	private HashOperations hashOperations;
     
	
	public UserTokensRepositoryImpl(RedisTemplate<String,UserToken> redisTemplate)
	{
		this.redisTemplate =redisTemplate;
		this.hashOperations=redisTemplate.opsForHash();
	}
	
	
	@Override
	public void save(UserToken userToken) {		
		logger.debug("in save userToken");
		hashOperations.put(Constants.USER_TOKEN_KEY, userToken.getUsername(), userToken);
		//redisTemplate.expire(key, timeout, unit)
	
	}

	@Override
	public UserToken find(String userNameToken) {					
		return (UserToken) hashOperations.get(Constants.USER_TOKEN_KEY, userNameToken);
	}

	@Override
	public void deleteToken(String userNameToken) {
		
		hashOperations.delete(Constants.USER_TOKEN_KEY,userNameToken);		
	}

	@Override
	public void updateToken( UserToken userToken) {
		save(userToken);
		
	}

}
