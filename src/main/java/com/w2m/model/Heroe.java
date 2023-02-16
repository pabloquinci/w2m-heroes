package com.w2m.model;

import jakarta.persistence.*;
import lombok.*;


@Table(name="HEROE")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Heroe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public Long getId() {
		return id;
	}

/*private Heroe(){
		throw new UnsupportedOperationException("This is a utility class and cannot be instantied")
	}*/


}
