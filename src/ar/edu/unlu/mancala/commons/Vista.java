package ar.edu.unlu.mancala.commons;

import java.util.LinkedList;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.IJugador;
import ar.edu.unlu.mancala.modelo.Ihoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.vista.consola.CartelAdvertencia;

public interface Vista {

	void mostrarMensaje(String string, CartelAdvertencia error);

	void mostrarTablero(Ihoyo[] tablero);

	void movimientos();

	void mostrarGanador(IJugador jugador, int i);

	void setControlador(MancalaController controlador);

	void iniciar();

	void mostrarJugadores(LinkedList<Jugador> jugadores);
	
}
