package com.w2m.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Heroe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private static Integer id;
	@Column
	private static String nombre;
	@Transient
	private static List<Superpoder> superpoderes;
	
	public static Integer getId() {
		return id;
	}
	public static String getNombre() {
		return nombre;
	}
	public static List<Superpoder> getSuperpoderes() {
		return superpoderes;
	}
	
	
	

}
