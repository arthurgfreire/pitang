package com.pitang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.demo.model.Carro;
import com.pitang.demo.model.Usuario;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{

}
