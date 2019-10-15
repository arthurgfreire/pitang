package com.pitang.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.demo.security.JwtValidator;
import com.pitang.demo.service.ICarroService;
import com.pitang.demo.service.IUsuarioService;
import com.pitang.demo.type.UsuarioLogadoType;
import com.pitang.demo.type.UsuarioType;
import com.pitang.demo.type.converter.UsuarioLogadoTypeConverter;
import com.pitang.demo.type.converter.UsuarioTypeConverter;

@RestController
public class CarroController {

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	ICarroService carroService;
	
	@Autowired
	private HttpServletRequest request;

	@Inject
	private UsuarioTypeConverter usuarioTypeConverter;
	@Inject
	private UsuarioLogadoTypeConverter usuarioLogadoTypeConverter;
	@Inject
	private JwtValidator jwtValidato;

	@RequestMapping(value="/me",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET)
	public ResponseEntity<UsuarioLogadoType> cadastrarUsuario() {
		String authorization = request.getHeader("Authorization");
		UsuarioLogadoType jwtUser = jwtValidato.validate(authorization.replace("Token ", ""));
		return  new ResponseEntity<>(usuarioLogadoTypeConverter.convertToType(usuarioService.usersId(jwtUser.getId()), jwtUser.getLastLogin()), HttpStatus.OK);
	}


}
