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

	/** Listar todos os carros pelo ID do usuario que está logado
	 * @param Integer id
	 * @return List<Carro>
	 * @author arthur.gomes.f.souza
	 */
	@Override
	public List<Carro> carsAll(Integer id) {
		return carroRepository.findAllByUsuarioId(id);
	}

	/** Buscar um carro pelo id do carro e pelo ID do usuario que está logado
	 * @param Integer id
	 * @param Integer idCarroLogado
	 * @return Carro
	 * @author arthur.gomes.f.souza
	 */
	@Override
	public Carro carsId(Integer id, Integer idCarroLogado) {
		return carroRepository.findByIdAndUsuarioId(id, idCarroLogado);
	}

	/** Buscar um carro especifico do usuario que está logado
	 * @param Integer id
	 * @param Integer idCarroLogado
	 * @author arthur.gomes.f.souza
	 */
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

	/** Cadastrar um carro em um usuario que está logado
	 * @param Integer idLogado
	 * @param Carro carro
	 * @return Carro
	 * @author arthur.gomes.f.souza
	 */
	@Override
	public Carro cadastrar(Integer idLogado, Carro carro) {
		validarCampos(carro);
		validarPlaca(carro);
		carro.setId(null);
		carro.setUsuario(new Usuario());
		carro.getUsuario().setId(idLogado);
		return carroRepository.save(carro);
	}

	/** Alterar um carro em um usuario que está logado
	 * @param Integer idLogado
	 * @param Integer id
	 * @param Carro carro
	 * @return Carro
	 * @author arthur.gomes.f.souza
	 */
	@Override
	public Carro alterar(Integer id,Integer idLogado, Carro carro) {
		if(id!=null&&id>0) {
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

	/** validar se os campos da tabela estão preenchidos corretamente
	 * @param Carro carro
	 * @author arthur.gomes.f.souza
	 */
	private void validarCampos(Carro carro) {
		if(Util.isStringNullOrEmpty(carro.getColor())||Util.isStringNullOrEmpty(carro.getLicensePlate())
				||Util.isStringNullOrEmpty(carro.getModel())||carro.getYear()<1){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,CAMPOS_NAO_PREENCHIDOS);
		}
	}
	
	/** validar se a placa do carro não existe em outro veiculo no metodo de cadastro
	 * @param Carro carro
	 * @author arthur.gomes.f.souza
	 */
	private void validarPlaca(Carro carro) {
		if(carroRepository.findByLicensePlate(carro.getLicensePlate())!=null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
		}
	}

	/** validar se a placa do carro não existe em outro veiculo no metodo de alteracao
	 * @param Carro carro
	 * @author arthur.gomes.f.souza
	 */
	private void validarPlacaAlteracao(Carro carro) {
		Carro car = carroRepository.findByLicensePlate(carro.getLicensePlate());
		if(car!=null &&	car.getId()!=carro.getId()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,PLACA_JA_EXISTENTE);
		}
	}

}
