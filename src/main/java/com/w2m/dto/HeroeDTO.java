package com.w2m.dto;

public class HeroeDTO {
	
	private String nombre;
	private Integer id;
	
	

	public HeroeDTO(String nombre, Integer id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
