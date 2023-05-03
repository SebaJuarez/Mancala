package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Tablero;

public interface Ivista {

	void iniciar();

	MancalaController getControlador();

	void setControlador(MancalaController controlador);

	void mostrarTablero(Tablero tablero);

	void informar(String string);

	void informar(Jugador modelo, String string);

	void mostrarGanador(Jugador obtenerGanador);

}