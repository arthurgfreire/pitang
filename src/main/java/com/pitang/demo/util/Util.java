package com.pitang.demo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Util {
	
    private static final String EMPTY_STRING = "";
    
	private static String KEY = "SECRET_TOKEN";

	private static final String TOKEN_HEADER = "Authentication";
	
	@SuppressWarnings("unused")
	public static boolean isStringNullOrEmpty(final String string) {
        boolean isNullOrEmpty = false;
        if (string == null || EMPTY_STRING.equals(string)) {
            isNullOrEmpty = true;
        }
        return isNullOrEmpty;
    }
	
	public static String md5(String senha){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		senha = hash.toString(16);			
		return senha;
	}
	
    public static String create(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static Jws<Claims> decode(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
    }

}
