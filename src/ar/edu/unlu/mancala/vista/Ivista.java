package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.controlador.JugadorController;
import ar.edu.unlu.mancala.controlador.MancalaController;

public interface Ivista {

	void iniciar();

	MancalaController getControlador();

	void setControlador(MancalaController controlador);
	
	void menuInicioSesion();

	void mostrarPartida(TableroLectura tablero, JugadorLectura jugadorMueve);

	void informar(String string);

	void informar(JugadorLectura modelo, String string);

	void mostrarGanador(JugadorLectura obtenerGanador);

	void mostrarSalaDeEspera();

	void setControladorJugador(JugadorController controladorJugador);

}