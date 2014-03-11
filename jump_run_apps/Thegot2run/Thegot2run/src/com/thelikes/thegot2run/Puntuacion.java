package com.thelikes.thegot2run;

import java.io.Serializable;

public class Puntuacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int score;
	private String fecha;
	
	

	public Puntuacion(String nombre, int score, String fecha) {
		super();
		this.nombre = nombre;
		this.score = score;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	
	

}
