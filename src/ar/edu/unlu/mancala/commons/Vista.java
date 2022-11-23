package ar.edu.unlu.mancala.commons;

import java.util.LinkedList;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.JugadorMostrable;
import ar.edu.unlu.mancala.modelo.HoyoMostrable;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.vista.consola.CartelAdvertencia;

public interface Vista {

	void mostrarMensaje(String string, CartelAdvertencia error);

	void mostrarTablero(HoyoMostrable[] tablero);

	void movimientos();

	void mostrarGanador(JugadorMostrable jugador, int i);

	void setControlador(MancalaController controlador);

	void iniciar();

	void mostrarJugadores(LinkedList<JugadorMostrable> jugadores);
	
}
