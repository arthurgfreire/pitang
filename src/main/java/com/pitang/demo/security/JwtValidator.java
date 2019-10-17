package com.pitang.demo.security;

import com.pitang.demo.model.JwtUser;
import com.pitang.demo.type.UsuarioLogadoType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class JwtValidator {

	private static final String KEY = "SECRET_TOKEN";
	private final String TOKEN_EXPIRADO = "Unauthorized - invalid session";
	private final String TOKEN_NAO_ENVIADO = "Unauthorized";

    public UsuarioLogadoType validate(String token) {
    	if(token==null||token.isEmpty()) {
    		throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,TOKEN_NAO_ENVIADO);
    	}

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
        	throw new RuntimeException(TOKEN_EXPIRADO);
        }

        return jwtUser;
    }
}
