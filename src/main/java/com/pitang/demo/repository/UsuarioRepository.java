package com.pitang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByLoginStartingWithIgnoreCase(String email);
	
	public Usuario findByEmailStartingWithIgnoreCase(String email);
    
    public Usuario findByLogin (String Login);
}
