package com.pitang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByLoginIgnoreCase(String login);
	
	public Usuario findByLoginIgnoreCaseAndIdNot(String login, Integer id);
	
	public Usuario findByEmailIgnoreCase(String email);
	
	public Usuario findByEmailIgnoreCaseAndIdNot(String email, Integer id);
    
    public Usuario findByLogin (String Login);
}
