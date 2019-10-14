package com.pitang.demo.type.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Column;

import com.pitang.demo.model.Carro;
import com.pitang.demo.type.CarroType;



@Named
public class CarroTypeConverter implements ITypeConverter<CarroType, Carro>{

	@Override
	public CarroType convertToType(Carro carro) {
		final CarroType carroType = new CarroType();
		carroType.setId(carro.getId());
		carroType.setColor(carro.getColor());
		carroType.setLicensePlate(carro.getLicensePlate());
		carroType.setModel(carro.getModel());
		carroType.setYear(carro.getYear());
		return carroType;
	}
	@Override
	public Carro convertToEntity(CarroType carroType) {
		final Carro carro = new Carro();
		carro.setId(carroType.getId());
		carro.setColor(carroType.getColor());
		carro.setLicensePlate(carroType.getLicensePlate());
		carro.setModel(carroType.getModel());
		carro.setYear(carroType.getYear());
		return carro;
	}
	@Override
	public List<CarroType> convertToType(List<Carro> listaCarro) {
		final List<CarroType> listaCarroType = new ArrayList<CarroType>();
		for (final Carro carro : listaCarro) {
			listaCarroType.add(convertToType(carro));
		}
		return listaCarroType;
	}
	@Override
	public List<Carro> convertToEntity(List<CarroType> listaCarroType) {
		final List<Carro> listaCarro = new ArrayList<Carro>();
		for (final CarroType carroType : listaCarroType) {
			listaCarro.add(convertToEntity(carroType));
		}
		return listaCarro;
	}

}

