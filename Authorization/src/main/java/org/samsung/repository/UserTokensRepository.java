package org.samsung.repository;

import org.samsung.model.UserToken;

public interface UserTokensRepository {

	public void save(UserToken userToken);
	
	public UserToken find(String userNameToken);
	
	public void deleteToken(String userNameToken);
	
	public void updateToken(UserToken userToken);
}
