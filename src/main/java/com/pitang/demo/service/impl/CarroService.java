package com.pitang.demo.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pitang.demo.model.Carro;
import com.pitang.demo.model.Usuario;
import com.pitang.demo.repository.CarroRepository;
import com.pitang.demo.repository.UsuarioRepository;
import com.pitang.demo.service.ICarroService;
import com.pitang.demo.util.Util;

@Service
public class CarroService implements ICarroService{


	@Inject
	private CarroRepository carroRepository;

	private final String CAMPOS_NAO_PREENCHIDOS = "Missing fields";
	private final String PLACA_JA_EXISTENTE = "License plate already exists";

	@Override
	public List<Carro> carsAll(Integer id) {
		return carroRepository.findAllByUsuarioId(id);
	}

	@Override
	public Carro carsId(Integer id, Integer idCarroLogado) {
		return carroRepository.findByIdAndUsuarioId(id, idCarroLogado);
	}

	@Override
	public void removerCarroId(Integer id, Integer idCarroLogado) {
		Carro carro = carroRepository.findByIdAndUsuarioId(id, idCarroLogado);
		if(carro!=null) {
			carroRepository.delete(carro);
		}else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Carro não existe");
		}
	}

	@Override
	public Carro cadastrar(Integer idLogado, Carro carro) {
		validarCampos(carro);
		validarPlaca(carro);
		carro.setId(null);
		carro.setUsuario(new Usuario());
		carro.getUsuario().setId(idLogado);
		return carroRepository.save(carro);
	}

	@Override
	public Carro alterar(Integer id,Integer idLogado, Carro carro) {
		if(id!=null) {
			Carro car = carroRepository.findByIdAndUsuarioId(id, idLogado);
			if(car!=null) {
				carro.setId(id);
				validarCampos(carro);
				validarPlacaAlteracao(carro);
				carro.setUsuario(new Usuario());
				carro.getUsuario().setId(idLogado);
				return carroRepository.save(carro);
			}else {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND,"Carro não existe");
			}
		}else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}

	private void validarCampos(Carro carro) {
		if(Util.isStringNullOrEmpty(carro.getColor())||Util.isStringNullOrEmpty(carro.getLicensePlate())
				||Util.isStringNullOrEmpty(carro.getModel())||carro.getYear()<1){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}

	private void validarPlaca(Carro carro) {
		if(carroRepository.findByLicensePlate(carro.getLicensePlate())!=null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
		}
	}

	private void validarPlacaAlteracao(Carro carro) {
		Carro car = carroRepository.findByLicensePlate(carro.getLicensePlate());
		if(car!=null &&	car.getId()!=carro.getId()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
		}
	}

}
