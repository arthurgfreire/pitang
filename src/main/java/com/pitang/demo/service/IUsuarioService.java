package com.pitang.demo.service;

import java.util.List;

import com.pitang.demo.model.Usuario;

/**
 * Interface do serviço de Usuarios.
 *
 */
public interface IUsuarioService {
	
	public Usuario cadastrar(final Usuario usuario);

	public List<Usuario> usersAll();

	public Usuario usersId(Integer id);

	public Usuario alterar(Usuario usuario, Integer id);
	
}
