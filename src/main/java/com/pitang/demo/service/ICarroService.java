package com.pitang.demo.service;

import java.util.List;

import com.pitang.demo.model.Carro;

/**
 * Interface do serviço de Carro.
 *
 */
public interface ICarroService {

	public List<Carro> carsAll(Integer id);
	
}
