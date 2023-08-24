package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

import ar.edu.unlu.mancala.vista.AgujeroLectura;

public abstract class Agujero implements Serializable, AgujeroLectura {

	private static final long serialVersionUID = 1L;
	protected int habas;

	public void ponerHaba() {
		this.habas++;
	}

	public int getHabas() {
		return habas;
	}

	public void setHabas(int habas) {
		this.habas = habas;
	}
}
