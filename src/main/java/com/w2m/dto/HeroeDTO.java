package com.w2m.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroeDTO {

	private Long id;
	private String nombre;



	/*public HeroeDTO(Long id,String nombre) {
		super();
		this.nombre = nombre;
		this.id = id;
	}*/
	
	

}
