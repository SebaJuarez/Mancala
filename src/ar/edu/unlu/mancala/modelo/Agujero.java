package ar.edu.unlu.mancala.modelo;

public abstract class Agujero {
	
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

	    public abstract boolean esCasa();

	    public abstract boolean esHoyo();
	    
	    public abstract int siguienteAgujero(int jugadorActual, int longuitudTablero);

		@Override
		public String toString() {
			return  this.getClass().getSimpleName() + "[habas=" + habas + ", indice=" + indice + ", jugador=" + jugador + "]";
		}



}
