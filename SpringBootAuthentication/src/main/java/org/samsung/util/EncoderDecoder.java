package org.samsung.util;

import java.util.Base64;

public class EncoderDecoder {

	
	public  static String encodePassword(String password)
	{
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
/*	public static String decodePassword(String encodedPassword)
	{
		return new String(Base64.getDecoder().decode(encodedPassword.getBytes()) );
	}*/
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pwd=EncoderDecoder.encodePassword("testMe@1234");
		System.out.println("encoded password"+pwd);
		
		System.out.println("decoded password :- "+EncoderDecoder.decodePassword(pwd));
				
	}*/

}
