package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.controlador.MancalaController;

public interface Ivista extends IVistaPartida, IVistaJugador {

	void iniciar();

	MancalaController getControlador();

	void setControlador(MancalaController controlador);

	void mostrarReglas();

}