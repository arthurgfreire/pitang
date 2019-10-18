package com.pitang.demo.type.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pitang.demo.model.Usuario;
import com.pitang.demo.type.UsuarioType;

@Named
public class UsuarioTypeConverter implements ITypeConverter<UsuarioType, Usuario> {

	@Inject 
	private transient CarroTypeConverter carroTypeConverter; 

	@Override
	public UsuarioType convertToType(Usuario usuario) {
		if(usuario!=null) {
			UsuarioType usuarioType = new UsuarioType();
			usuarioType.setId(usuario.getId());
			usuarioType.setEmail(usuario.getEmail());
			usuarioType.setFirstName(usuario.getFirstName());
			usuarioType.setLastName(usuario.getLastName());
			usuarioType.setPassword(usuario.getPassword());
			usuarioType.setLogin(usuario.getLogin());
			usuarioType.setPhone(usuario.getPhone());
			usuarioType.setBirthday(usuario.getBirthday());
			if(usuario.getContador()==null) {
				usuarioType.setContador(0);
			}else {
				usuarioType.setContador(usuario.getContador());
			}
			usuarioType.setCars(carroTypeConverter.convertToType(usuario.getCars()));
			return usuarioType;
		}
		return null;
	}

	@Override
	public Usuario convertToEntity(UsuarioType usuarioType) {
		if(usuarioType!=null) {
			Usuario usuario = new Usuario();
			usuario.setId(usuarioType.getId());
			usuario.setEmail(usuarioType.getEmail());
			usuario.setFirstName(usuarioType.getFirstName());
			usuario.setLastName(usuarioType.getLastName());
			usuario.setPassword(usuarioType.getPassword());
			usuario.setLogin(usuarioType.getLogin());
			usuario.setPhone(usuarioType.getPhone());
			usuario.setBirthday(usuarioType.getBirthday());
			usuario.setContador(0);
			try {
				usuario.setCars(carroTypeConverter.convertToEntity(usuarioType.getCars()));
			}catch (Exception e) {
				usuario = null;
			}
			return usuario;
		}
		return null;
	}

	@Override
	public List<UsuarioType> convertToType(List<Usuario> listaUsuario) {
		final List<UsuarioType> listaUsuarioType = new ArrayList<UsuarioType>();
		for (final Usuario usuario : listaUsuario) {
			listaUsuarioType.add(convertToType(usuario));
		}
		return listaUsuarioType;
	}

	@Override
	public List<Usuario> convertToEntity(List<UsuarioType> listaUsuarioType) {
		final List<Usuario> listaUsuario = new ArrayList<Usuario>();
		for (final UsuarioType usuarioType : listaUsuarioType) {
			listaUsuario.add(convertToEntity(usuarioType));
		}
		return listaUsuario;
	}

}

