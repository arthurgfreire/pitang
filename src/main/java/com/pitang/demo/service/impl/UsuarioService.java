package com.pitang.demo.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.mindrot.jbcrypt.BCrypt;

import com.pitang.demo.model.Carro;
import com.pitang.demo.model.Usuario;
import com.pitang.demo.repository.CarroRepository;
import com.pitang.demo.repository.UsuarioRepository;
import com.pitang.demo.service.IUsuarioService;
import com.pitang.demo.util.Util;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuarioRepository usuarioReposity;

	@Inject
	private CarroRepository carroRepository;

	private final String LOGIN_INEXISTENTE_OU_SENHA_INVÁLIDA = "Invalid login or password";
	private final String EMAIL_JA_EXISTENTE = "Email already exists";
	private final String LOGIN_JA_EXISTENTE = "Login already exists";
	private final String CAMPOS_INVALIDOS = "Invalid fields";
	private final String CAMPOS_NAO_PREENCHIDOS = "Missing fields";


	public Usuario cadastrar(Usuario usuario) {
		camposNaoPreenchido(usuario); 
		existeEmail(usuario.getEmail());
		existeLogin(usuario.getLogin());
		usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
		usuario.setCars(carroRepository.saveAll(usuario.getCars()));
		return usuarioReposity.save(usuario);
	}
	
	@Override
	public List<Usuario> usersAll() {
		return usuarioReposity.findAll();
	}
	
	@Override
	public Usuario usersId(Integer id) {
		return usuarioReposity.findById(id).orElse(null);
	}

	private boolean camposNaoPreenchido(Usuario usuario) {
		boolean retorno = false;
		if(Util.isStringNullOrEmpty(usuario.getEmail())||Util.isStringNullOrEmpty(usuario.getFirstName())||
				Util.isStringNullOrEmpty(usuario.getLastName())||Util.isStringNullOrEmpty(usuario.getPassword())||
				Util.isStringNullOrEmpty(usuario.getLogin())||Util.isStringNullOrEmpty(usuario.getPhone())||
				usuario.getBirthday()==null||usuario.getCars().isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
		for(Carro car: usuario.getCars()) {
			if(Util.isStringNullOrEmpty(car.getColor())||Util.isStringNullOrEmpty(car.getLicensePlate())
					||Util.isStringNullOrEmpty(car.getModel())||car.getYear()<1){
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
			}
		}	
		return retorno;
	}	

	private void existeEmail(String email) {
		if(usuarioReposity.findByEmailIgnoreCase(email)!=null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,EMAIL_JA_EXISTENTE);
		}
	}

	private void existeLogin(String login) {
		if(usuarioReposity.findByLoginIgnoreCase(login)!=null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,LOGIN_JA_EXISTENTE);
		}
	}	
}
