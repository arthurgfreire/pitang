package com.pitang.demo.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.demo.service.IUsuarioService;
import com.pitang.demo.type.UsuarioType;
import com.pitang.demo.type.converter.UsuarioTypeConverter;

@RestController
@RequestMapping("/api")
public class CarroController {

	@Autowired
	IUsuarioService usuarioService;

	@Inject
	private UsuarioTypeConverter usuarioTypeConverter;

	@RequestMapping(method=RequestMethod.POST,value="/cars",
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioType> cadastrarUsuario(@RequestBody UsuarioType usuarioType) {
		int x;
		x=1;
		return  new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.cadastrar(
				usuarioTypeConverter.convertToEntity(usuarioType))), HttpStatus.CREATED);
	}

//	@RequestMapping(method=RequestMethod.GET,value="/users",
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<UsuarioType>> listaUsuario() {		   
//		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.usersAll()), HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/users/{id}",
//			produces = { "application/json" }, 
//			method = RequestMethod.GET)
//	public ResponseEntity<UsuarioType> listarUsuarioId(@PathVariable("id") Integer  id) {
//		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.usersId(id)), HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/users/{id}",
//			produces = { "application/json" }, 
//			method = RequestMethod.PUT)
//	public ResponseEntity<UsuarioType> alterarUsuario(@RequestBody UsuarioType usuarioType, @PathVariable("id") Integer  id) {
//		return new ResponseEntity<>(usuarioTypeConverter.convertToType(usuarioService.alterar(usuarioTypeConverter.convertToEntity(usuarioType),id)), HttpStatus.CREATED);
//	}
//	
//	@RequestMapping(value = "/users/{id}",
//			produces = { "application/json" }, 
//			method = RequestMethod.DELETE)
//	public ResponseEntity<UsuarioType> removerUsiario(@PathVariable("id") Integer  id) {
//		usuarioService.removerUserId(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}


}