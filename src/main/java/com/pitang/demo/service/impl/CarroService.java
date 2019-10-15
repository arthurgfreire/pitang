package com.pitang.demo.service.impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.demo.model.Usuario;
import com.pitang.demo.repository.CarroRepository;
import com.pitang.demo.repository.UsuarioRepository;
import com.pitang.demo.service.ICarroService;

@Service
public class CarroService implements ICarroService{

	@Autowired
	private UsuarioRepository usuarioReposity;

	@Inject
	private CarroRepository carroRepository;


	@Override
	public Usuario usersLogado(Integer id) {
		return usuarioReposity.findById(id).orElse(null);
	}

}
