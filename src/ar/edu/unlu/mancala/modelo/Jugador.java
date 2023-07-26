package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

import ar.edu.unlu.mancala.vista.JugadorLectura;

public class Jugador implements JugadorLectura, Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String contrasenia;
	private int ganadas;
	private int perdidas;
	private int empatadas;
	
	public int getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(int perdidas) {
		this.perdidas = perdidas;
	}

	public int getEmpatadas() {
		return empatadas;
	}

	public void setEmpatadas(int empatadas) {
		this.empatadas = empatadas;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGanadas() {
		return ganadas;
	}

	public void setGanadas(int ganadas) {
		this.ganadas = ganadas;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
