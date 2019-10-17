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
import org.springframework.web.server.ResponseStatusException;

import com.pitang.demo.security.JwtValidator;
import com.pitang.demo.service.ICarroService;
import com.pitang.demo.service.IUsuarioService;
import com.pitang.demo.type.UsuarioLogadoType;
import com.pitang.demo.type.UsuarioType;
import com.pitang.demo.type.converter.UsuarioLogadoTypeConverter;
import com.pitang.demo.type.converter.UsuarioTypeConverter;

@RestController
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	private HttpServletRequest request;

	@Inject
	private UsuarioTypeConverter usuarioTypeConverter;
	
	@Inject
	private UsuarioLogadoTypeConverter usuarioLogadoTypeConverter;
	
	@Inject
	private JwtValidator jwtValidato;

	@RequestMapping(method=RequestMethod.POST,value="/users",
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioType> cadastrarUsuario(@RequestBody UsuarioType usuarioType) {
		return  new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.cadastrar(
				usuarioTypeConverter.convertToEntity(usuarioType))), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.GET,value="/users",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UsuarioType>> listaUsuario() {		   
		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.usersAll()), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.GET)
	public ResponseEntity<UsuarioType> listarUsuarioId(@PathVariable("id") Integer  id) {
		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.usersId(id)), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}",
			produces = { "application/json" }, 
			method = RequestMethod.PUT)
	public ResponseEntity<UsuarioType> alterarUsuario(@RequestBody UsuarioType usuarioType, @PathVariable("id") Integer  id) {
		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.alterar(usuarioTypeConverter.convertToEntity(usuarioType),id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.DELETE)
	public ResponseEntity<UsuarioType> removerUsiario(@PathVariable("id") Integer  id) {
		usuarioService.removerUserId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/signin",
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestHeader("login") String login,@RequestHeader("password") String password) {
		return  new ResponseEntity<>(usuarioService.login(login,password), HttpStatus.OK);
	}

	@RequestMapping(value="/me",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET)
	public ResponseEntity<UsuarioLogadoType> cadastrarUsuario() {
		String authorization = request.getHeader("Authorization");
		if(authorization==null) {
			authorization="";
    	}
		UsuarioLogadoType jwtUser = jwtValidato.validate(authorization.replace("Token ", ""));
		return  new ResponseEntity<>(usuarioLogadoTypeConverter.convertToType(usuarioService.usersId(jwtUser.getId()), jwtUser.getLastLogin()), HttpStatus.OK);
	}


}
