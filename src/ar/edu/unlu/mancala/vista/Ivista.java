package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Jugador;

public interface Ivista {

	void iniciar();

	MancalaController getControlador();

	void setControlador(MancalaController controlador);

	void mostrarPartida(TableroLectura tablero, Jugador jugadorMueve);

	void informar(String string);

	void informar(JugadorLectura modelo, String string);

	void mostrarGanador(JugadorLectura obtenerGanador);

	void mostrarSalaDeEspera();


}