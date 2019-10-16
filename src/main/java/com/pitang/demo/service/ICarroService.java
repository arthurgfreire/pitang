package com.pitang.demo.service;

import java.util.List;

import com.pitang.demo.model.Carro;

/**
 * Interface do serviço de Carro.
 *
 */
public interface ICarroService {

	public List<Carro> carsAll(Integer id);

	public Carro carsId(Integer id, Integer idCarroLogado);

	public void removerCarroId(Integer id, Integer idCarroLogado);

	public Carro cadastrar(Integer idLogado, Carro carro);

	public Carro alterar(Integer id, Integer idLogado, Carro carro);
	
}
