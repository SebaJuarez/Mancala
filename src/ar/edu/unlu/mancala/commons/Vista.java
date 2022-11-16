package ar.edu.unlu.mancala.commons;

import java.util.LinkedList;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.vista.consola.CartelAdvertencia;

public interface Vista {

	void mostrarMensaje(String string, CartelAdvertencia error);

	void mostrarTablero(Hoyo[] tablero);

	void movimientos();

	void mostrarGanador(Jugador jugador, int i);

	void mostrarJugadores(LinkedList<Jugador> jugadores);

	void setControlador(MancalaController controlador);

	void iniciar();
	
}
