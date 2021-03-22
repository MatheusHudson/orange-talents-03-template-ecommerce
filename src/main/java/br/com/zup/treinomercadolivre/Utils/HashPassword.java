package br.com.zup.treinomercadolivre.Utils;


import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class HashPassword {
	
	public static String hashPassword(String senha)  {
	SecureRandom random = new SecureRandom();
	

	 String salt = BCrypt.gensalt(4, random);

	String hashedPassword = BCrypt.hashpw(senha, salt);
	return hashedPassword;

	}
	
	
}

