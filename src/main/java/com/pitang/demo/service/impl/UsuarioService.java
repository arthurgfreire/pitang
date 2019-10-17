package com.pitang.demo.service.impl;

import java.time.LocalDate;
import java.util.Date;
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
import com.pitang.demo.security.JwtGenerator;
import com.pitang.demo.service.IUsuarioService;
import com.pitang.demo.util.Util;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuarioRepository usuarioReposity;

	@Inject
	private CarroRepository carroRepository;

	@Inject
	private JwtGenerator jwtGenerator;

	private final String LOGIN_INEXISTENTE_OU_SENHA_INVÁLIDA = "Invalid login or password";
	private final String EMAIL_JA_EXISTENTE = "Email already exists";
	private final String LOGIN_JA_EXISTENTE = "Login already exists";
	private final String CAMPOS_INVALIDOS = "Invalid fields";
	private final String CAMPOS_NAO_PREENCHIDOS = "Missing fields";
	private final String PLACA_JA_EXISTENTE = "License plate already exists";


	public Usuario cadastrar(Usuario usuario) {
		usuario.setId(null);
		camposNaoPreenchido(usuario); 
		existeEmail(usuario.getEmail());
		existeLogin(usuario.getLogin());
		validarPlaca(usuario.getCars());
		usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
		usuario.setCreatedAt(LocalDate.now());
		usuario.setCars(carroRepository.saveAll(usuario.getCars()));
		return usuarioReposity.save(usuario);
	}

	@Override
	public Usuario alterar(Usuario usuario,Integer id) {
		if(id!=null) {
			Usuario usuarioExiste =  usuarioReposity.findById(id).orElse(null);
			if(usuarioExiste!=null) {
				usuario.setId(usuarioExiste.getId());
				camposNaoPreenchido(usuario); 
				existeEmailAlteracao(usuario.getEmail(),id);
				existeLoginAlteracao(usuario.getLogin(),id);
				validarPlacaAlteracao(usuario.getCars(),id);
				usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
				carroRepository.deleteAll(usuarioExiste.getCars());
				usuario.setCreatedAt(usuarioExiste.getCreatedAt());
				usuario.setCars(carroRepository.saveAll(usuario.getCars()));
				return usuarioReposity.save(usuario);
			}else {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND,CAMPOS_INVALIDOS);
			}
		}else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}

	@Override
	public List<Usuario> usersAll() {
		return usuarioReposity.findAll();
	}

	@Override
	public Usuario usersId(Integer id) {
		return usuarioReposity.findById(id).orElse(null);
	}

	@Override
	public void removerUserId(Integer id) {
		Usuario users = usuarioReposity.findById(id).orElse(null);
		if(users!=null) {
			if(!users.getCars().isEmpty()) {
				carroRepository.deleteAll(users.getCars());
			}
			usuarioReposity.deleteById(id);
		}else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}

	@Override
	public String login(String login, String password) {
		camposLoginPasswordNaoPreenchido(login,password);
		Usuario usuarioLogado = usuarioReposity.findByLogin(login);
		if(usuarioLogado==null||
				!BCrypt.checkpw(password, usuarioLogado.getPassword())) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,LOGIN_INEXISTENTE_OU_SENHA_INVÁLIDA);
		}
		return jwtGenerator.generate(usuarioLogado,LocalDate.now());
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
	private void existeEmailAlteracao(String email, Integer id) {
		Usuario a = usuarioReposity.findByEmailIgnoreCaseAndIdNot(email, id);
		if(usuarioReposity.findByEmailIgnoreCaseAndIdNot(email,id)!=null) {
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

	private void existeLoginAlteracao(String login, Integer id) {
		if(usuarioReposity.findByLoginIgnoreCaseAndIdNot(login, id)!=null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,LOGIN_JA_EXISTENTE);
		}
	}	

	private void camposLoginPasswordNaoPreenchido(final String login, final String password) {
		if(Util.isStringNullOrEmpty(login)||
				Util.isStringNullOrEmpty(password)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}

	private void validarPlaca(List<Carro> carros) {
		for(Carro carro:carros) {
			int x=0;
			for(Carro car:carros) {
				if(car.getLicensePlate().equalsIgnoreCase(carro.getLicensePlate())) {
					x=x+1;
					if(x==2) {
						throw new ResponseStatusException(
								HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
					}
				}
			}
			if(carroRepository.findByLicensePlate(carro.getLicensePlate())!=null) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
			}
		}
	}

	private void validarPlacaAlteracao(List<Carro> carros, Integer id) {
		for(Carro carro:carros) {
			int x=0;
			for(Carro car:carros) {
				if(car.getLicensePlate().equalsIgnoreCase(carro.getLicensePlate())) {
					x=x+1;
					if(x==2) {
						throw new ResponseStatusException(
								HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
					}
				}
			}
			Carro cars = carroRepository.findByLicensePlate(carro.getLicensePlate());
			if(cars!=null&&cars.getUsuario().getId()!=id) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
			}
		}
	}
}
