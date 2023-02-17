package com.w2m.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name="HEROE")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Heroe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column
	private String nombre;


/*private Heroe(){
		throw new UnsupportedOperationException("This is a utility class and cannot be instantied")
	}*/


}
