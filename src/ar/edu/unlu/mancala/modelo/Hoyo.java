package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

public class Hoyo implements HoyoMostrable,Serializable{

	private static final long serialVersionUID = 1L;
	private Posicion posicionAlfabetica;
	private int cantHabas = 0;
	private final int longuitudTablero = 14;
	
	public Hoyo(Posicion posicionAlfabetica) {
		this.posicionAlfabetica = posicionAlfabetica;
	}
	
	public void ponerHaba() {
		this.cantHabas ++;
	}
	
	public void ponerHaba(int cantidadHaba) {
		this.cantHabas += cantidadHaba;
	}
	
	public int tomarHabas() {
		int habas = this.cantHabas;
		this.cantHabas = 0;
		return habas;
	}
	
	@Override
	public int getCantHabas() {
		return this.cantHabas;
	}
	
	public Posicion siguienteHoyo() {
		return Posicion.getPosicionPorValor(((this.posicionAlfabetica.ordinal()+1) % this.longuitudTablero));
	}

	public Posicion getPosicionAlfabetica() {
		return posicionAlfabetica;
	}
}
