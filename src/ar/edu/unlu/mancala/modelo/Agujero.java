package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

import ar.edu.unlu.mancala.vista.AgujeroLectura;

public abstract class Agujero implements Serializable , AgujeroLectura{
	
	private static final long serialVersionUID = 1L;
	protected int habas;
	protected int indice;
	protected int jugador;

	    public Agujero(int habas , int indice) {
	        this.habas = habas;
	        this.indice = indice;
	        this.jugador = indice <= 6? 1 : 2;
	    }

	    public void ponerHaba() {
	    	this.habas ++;
	    }
	    
	    public int getHabas() {
	        return habas;
	    }
	    
	    public int getJugador() {
	    	return this.jugador;
	    }

	    public void setHabas(int habas) {
	        this.habas = habas;
	    }
	    
	    public int getIndice() {
	    	return this.indice;
	    }
	    
	    public abstract int siguienteAgujero(int jugadorActual, int longuitudTablero);

		@Override
		public String toString() {
			return  this.getClass().getSimpleName() + "[habas=" + habas + ", indice=" + indice + ", jugador=" + jugador + "]";
		}



}
