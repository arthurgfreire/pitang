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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.demo.security.JwtValidator;
import com.pitang.demo.service.ICarroService;
import com.pitang.demo.type.CarroType;
import com.pitang.demo.type.UsuarioLogadoType;
import com.pitang.demo.type.UsuarioType;
import com.pitang.demo.type.converter.CarroTypeConverter;

@RestController
public class CarroController {
	
	@Autowired
	ICarroService carroService;
	
	@Autowired
	private HttpServletRequest request;

	@Inject
	private CarroTypeConverter carroTypeConverter;
	
	@Inject
	private JwtValidator jwtValidato;
	
	@RequestMapping(value="/cars",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET)
	public ResponseEntity<List<CarroType>> carsAll() {
		return new ResponseEntity<>(carroTypeConverter.convertToType(
				carroService.carsAll(usuarioLogado().getId())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cars/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.GET)
	public ResponseEntity<CarroType> buscarPorId(@PathVariable("id") Integer  id) {
		return new ResponseEntity<>(carroTypeConverter.convertToType(
				carroService.carsId(id, usuarioLogado().getId())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cars/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = RequestMethod.DELETE)
	public ResponseEntity<UsuarioType> removerCar(@PathVariable("id") Integer  id) {
		carroService.removerCarroId(id, usuarioLogado().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/cars",
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarroType> cadastrarUsuario(@RequestBody CarroType carroType) {
		return  new ResponseEntity<>(carroTypeConverter.convertToType(carroService.cadastrar(usuarioLogado().getId(),
				carroTypeConverter.convertToEntity(carroType))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/cars/{id}",
			produces = { "application/json" }, 
			method = RequestMethod.PUT)
	public ResponseEntity<CarroType> alterarUsuario(@RequestBody CarroType carroType, @PathVariable("id") Integer  id) {
		return new ResponseEntity<>(carroTypeConverter.convertToType(carroService.alterar(id,
				usuarioLogado().getId(),carroTypeConverter.convertToEntity(carroType))), HttpStatus.OK);
	}
	
	private UsuarioLogadoType usuarioLogado() {
		return jwtValidato.validate(request.getHeader("Authorization").replace("Token ", ""));
	}

}
