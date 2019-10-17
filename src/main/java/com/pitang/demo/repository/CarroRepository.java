package com.pitang.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.demo.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{

	public List<Carro> findAllByUsuarioId (Integer id);
	
	public Carro findByIdAndUsuarioId (Integer id, Integer idCarroLogado);
	
	public Carro findByLicensePlate (String placa);
	
	public Carro findByIdNotAndLicensePlate (Integer id,String placa);
}
