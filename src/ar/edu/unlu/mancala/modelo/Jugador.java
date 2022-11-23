package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

public class Jugador implements Serializable, JugadorMostrable{
	private static final long serialVersionUID = 1L;
	public static int ids = 0;
	private int id = ids ++;
	private String nombre;
	private int cantPartidasJugadas = 0;
	private int cantPartidasGanadas = 0;
	private int partidasEmpatadas = 0;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public void incPartidasJugadas() {
		this.cantPartidasJugadas ++;
	}
	
	public void incPartidasGanadas() {
		this.cantPartidasGanadas ++;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPartidasJugadas() {
		return this.cantPartidasJugadas;
	}
	
	public int getPartidasGanadas() {
		return this.cantPartidasGanadas;
	}
	
	public int getId() {
		return this.id;
	}

	public void incPartidasEmpatadas() {
		this.partidasEmpatadas ++;
	}
	
	public int getPartidasEmpatadas() {
		return this.partidasEmpatadas;
	}
}
