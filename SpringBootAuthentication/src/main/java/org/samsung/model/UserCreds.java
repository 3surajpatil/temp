package org.samsung.model;

public class UserCreds {

	private int User_Code;	
	private String UserName;
	private String Password;
	
	public UserCreds(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}

	public UserCreds(){
		
	}
	
	public int getUser_Code() {
		return User_Code;
	}

	public void setUser_Code(int user_Code) {
		User_Code = user_Code;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
