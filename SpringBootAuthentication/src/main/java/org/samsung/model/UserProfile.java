package org.samsung.model;

import java.util.Date;

public class UserProfile {

	int User_Code;	
	String First_Name;
	String Last_Name;
	String Email_Id;
	String Password;
	String Access_Type;
	String Status;
	Date Last_Login_TS;
	Date Last_Pwd_Changed;
	Date Create_Date;
	String Created_By;
	Date Modified_Date;
	String Modified_By;
	int Bad_Pwd_Cnt;
	String Comments;
	int Incorrect_Login_Attempts;
	int Pwd_Change_Days_Cnt;
	
	public int getUser_Code() {
		return User_Code;
	}
	public void setUser_Code(int user_Code) {
		User_Code = user_Code;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getAccess_Type() {
		return Access_Type;
	}
	public void setAccess_Type(String access_Type) {
		Access_Type = access_Type;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Date getLast_Login_TS() {
		return Last_Login_TS;
	}
	public void setLast_Login_TS(Date last_Login_TS) {
		Last_Login_TS = last_Login_TS;
	}
	public Date getLast_Pwd_Changed() {
		return Last_Pwd_Changed;
	}
	public void setLast_Pwd_Changed(Date last_Pwd_Changed) {
		Last_Pwd_Changed = last_Pwd_Changed;
	}
	public Date getCreate_Date() {
		return Create_Date;
	}
	public void setCreate_Date(Date create_Date) {
		Create_Date = create_Date;
	}
	public String getCreated_By() {
		return Created_By;
	}
	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}
	public Date getModified_Date() {
		return Modified_Date;
	}
	public void setModified_Date(Date modified_Date) {
		Modified_Date = modified_Date;
	}
	public String getModified_By() {
		return Modified_By;
	}
	public void setModified_By(String modified_By) {
		Modified_By = modified_By;
	}
	public int getBad_Pwd_Cnt() {
		return Bad_Pwd_Cnt;
	}
	public void setBad_Pwd_Cnt(int bad_Pwd_Cnt) {
		Bad_Pwd_Cnt = bad_Pwd_Cnt;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public int getIncorrect_Login_Attempts() {
		return Incorrect_Login_Attempts;
	}
	public void setIncorrect_Login_Attempts(int incorrect_Login_Attempts) {
		Incorrect_Login_Attempts = incorrect_Login_Attempts;
	}
	public int getPwd_Change_Days_Cnt() {
		return Pwd_Change_Days_Cnt;
	}
	public void setPwd_Change_Days_Cnt(int pwd_Change_Days_Cnt) {
		Pwd_Change_Days_Cnt = pwd_Change_Days_Cnt;
	}
	
}
