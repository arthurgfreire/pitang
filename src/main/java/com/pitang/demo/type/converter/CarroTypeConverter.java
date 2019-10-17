package com.pitang.demo.type.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.pitang.demo.model.Carro;
import com.pitang.demo.type.CarroType;



@Named
public class CarroTypeConverter implements ITypeConverter<CarroType, Carro>{

	@Override
	public CarroType convertToType(Carro carro) {
		if(carro!=null) {
			final CarroType carroType = new CarroType();
			carroType.setId(carro.getId());
			carroType.setColor(carro.getColor());
			carroType.setLicensePlate(carro.getLicensePlate());
			carroType.setModel(carro.getModel());
			carroType.setYear(carro.getYear());
			if(carro.getContador()==null) {
				carroType.setContador(0);
			}else {
				carroType.setContador(carro.getContador());
			}
			return carroType;
		}
		return null;
	}
	@Override
	public Carro convertToEntity(CarroType carroType) {
		if(carroType!=null) {
			final Carro carro = new Carro();
			carro.setId(carroType.getId());
			carro.setColor(carroType.getColor());
			carro.setLicensePlate(carroType.getLicensePlate());
			carro.setModel(carroType.getModel());
			carro.setYear(carroType.getYear());
			if(carroType.getContador()==null) {
				carro.setContador(0);
			}else {
				carro.setContador(carroType.getContador());
			}
			return carro;
		}
		return null;
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

