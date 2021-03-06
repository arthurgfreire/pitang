package com.pitang.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Carro {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	private String licensePlate;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private String color;
	@Column(nullable = false)
	private Integer contador;
	@ManyToOne
    @JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getContador() {
		return contador;
	}
	public void setContador(Integer contador) {
		this.contador = contador;
	}
	
}
