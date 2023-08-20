package ar.edu.unlu.mancala.modelo;

public interface Movimiento {
	void distribuirHabas(TableroN tablero, Hoyo hoyo);
	void tomarHabasOpuestas(TableroN tablero, Hoyo hoyo);
}
