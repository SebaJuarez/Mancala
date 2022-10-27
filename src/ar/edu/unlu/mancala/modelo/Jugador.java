package ar.edu.unlu.mancala.modelo;

public class Jugador {
	private String nombre;
	private int cantPartidasJugadas = 0;
	private int cantPartidasGanadas = 0;
	
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
	
}
