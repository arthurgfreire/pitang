package com.pitang.demo.security;

import com.pitang.demo.model.JwtUser;
import com.pitang.demo.type.UsuarioLogadoType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

	private static final String KEY = "SECRET_TOKEN";

    public UsuarioLogadoType validate(String token) {

    	UsuarioLogadoType jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new UsuarioLogadoType();

            jwtUser.setLogin(body.getSubject());
            jwtUser.setId(Integer.valueOf((String) body.get("userId")));
            jwtUser.setLastLogin(LocalDate.parse((String) body.get("lastLogin")));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
