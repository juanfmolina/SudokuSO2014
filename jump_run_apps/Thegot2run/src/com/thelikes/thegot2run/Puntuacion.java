package com.thelikes.thegot2run;

import java.io.Serializable;

public class Puntuacion implements Serializable {
	
	private String nombre;
	private String score;
	
	public Puntuacion(String nombre, String score) {
		super();
		this.nombre = nombre;
		this.score = score;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	

}
