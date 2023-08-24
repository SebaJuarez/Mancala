package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.Objects;

import ar.edu.unlu.mancala.vista.JugadorLectura;

public class Jugador implements JugadorLectura, Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String contrasenia;
	private int ganadas;

	private int perdidas;
	private int empatadas;
	
	private int totalJugadas;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public void incGanadas() {
		this.ganadas =+ 1;
		this.totalJugadas =+ 1;
	}
	
	public void incPerdidas() {
		this.perdidas =+ 1;
		this.totalJugadas =+ 1;
	}
	
	public void incEmpatadas() {
		this.empatadas =+ 1;
		this.totalJugadas =+ 1;
	}
	
	public double winRate() {
		return this.ganadas / this.totalJugadas;
	}
	
	public double loseRate() {
		return this.perdidas / this.totalJugadas;
	}
	
	public double drawnRate() {
		return this.empatadas / this.totalJugadas;
	}
}
