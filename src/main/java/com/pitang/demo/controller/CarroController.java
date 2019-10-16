package com.pitang.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.demo.security.JwtValidator;
import com.pitang.demo.service.ICarroService;
import com.pitang.demo.type.CarroType;
import com.pitang.demo.type.UsuarioLogadoType;
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
	public ResponseEntity<List<CarroType>> cadastrarUsuario() {
		String authorization = request.getHeader("Authorization");
		UsuarioLogadoType jwtUser = jwtValidato.validate(authorization.replace("Token ", ""));
		return new ResponseEntity<>(carroTypeConverter.convertToType(carroService.carsAll(jwtUser.getId())), HttpStatus.OK);
	}

}
