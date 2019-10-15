package com.pitang.demo.type.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pitang.demo.model.Usuario;
import com.pitang.demo.type.UsuarioLogadoType;

@Named
public class UsuarioLogadoTypeConverter implements ITypeConverter<UsuarioLogadoType, Usuario> {

	@Inject 
	private transient CarroTypeConverter carroTypeConverter;
	
	@Override
	public UsuarioLogadoType convertToType(Usuario usuario) {
		UsuarioLogadoType usuarioType = new UsuarioLogadoType();

		usuarioType.setEmail(usuario.getEmail());
		usuarioType.setFirstName(usuario.getFirstName());
		usuarioType.setLastName(usuario.getLastName());
		usuarioType.setLogin(usuario.getLogin());
		usuarioType.setPhone(usuario.getPhone());
		usuarioType.setBirthday(usuario.getBirthday());
		usuarioType.setCreatedAt(usuario.getCreatedAt());
		return usuarioType;
	}

	@Override
	public Usuario convertToEntity(UsuarioLogadoType usuarioType) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioType.getEmail());
		usuario.setFirstName(usuarioType.getFirstName());
		usuario.setLastName(usuarioType.getLastName());
		usuario.setLogin(usuarioType.getLogin());
		usuario.setPhone(usuarioType.getPhone());
		usuario.setBirthday(usuarioType.getBirthday());
		usuario.setCreatedAt(usuarioType.getCreatedAt());
		return usuario;
	}

	@Override
	public List<UsuarioLogadoType> convertToType(List<Usuario> listaUsuario) {
		final List<UsuarioLogadoType> listaUsuarioLogadoType = new ArrayList<UsuarioLogadoType>();
		for (final Usuario usuario : listaUsuario) {
			listaUsuarioLogadoType.add(convertToType(usuario));
		}
		return listaUsuarioLogadoType;
	}

	@Override
	public List<Usuario> convertToEntity(List<UsuarioLogadoType> listaUsuarioLogadoType) {
		final List<Usuario> listaUsuario = new ArrayList<Usuario>();
		for (final UsuarioLogadoType usuarioType : listaUsuarioLogadoType) {
			listaUsuario.add(convertToEntity(usuarioType));
		}
		return listaUsuario;
	}
	
	public UsuarioLogadoType convertToType(Usuario usuario, LocalDate date) {
		UsuarioLogadoType usuarioType = new UsuarioLogadoType();

		usuarioType.setId(usuario.getId());
		usuarioType.setEmail(usuario.getEmail());
		usuarioType.setFirstName(usuario.getFirstName());
		usuarioType.setLastName(usuario.getLastName());
		usuarioType.setLogin(usuario.getLogin());
		usuarioType.setPhone(usuario.getPhone());
		usuarioType.setBirthday(usuario.getBirthday());
		usuarioType.setCreatedAt(usuario.getCreatedAt());
		usuarioType.setLastLogin(date);
		usuarioType.setCars(carroTypeConverter.convertToType(usuario.getCars()));
		return usuarioType;
	}

}

