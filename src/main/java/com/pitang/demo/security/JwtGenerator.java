package com.pitang.demo.security;

import com.pitang.demo.model.JwtUser;
import com.pitang.demo.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
	private static final String KEY = "SECRET_TOKEN";

    public String generate(Usuario jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getLogin());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", "admin");


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }
}
