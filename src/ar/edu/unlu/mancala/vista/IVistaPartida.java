package ar.edu.unlu.mancala.vista;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.modelo.estados.partida.jugador.EstadoJugadorPartida;

public interface IVistaPartida extends IVistaComunes {

	void mostrarGanador(JugadorLectura obtenerGanador);

	void mostrarPerdedor(JugadorLectura jugador);

	void mostrarEmpate(JugadorLectura jugador);

	void mostrarPartida(List<LadoTablero> ladosTablero, JugadorLectura jugadorMueve, TipoPartida tipoPartida,
			JugadorLectura yo);

	void mostrarSalaDeEspera();

	default List<LadoTablero> ordenarLados(List<LadoTablero> ladosTablero, JugadorLectura yo) {
		Queue<LadoTablero> ladosOrdenados = new LinkedList<LadoTablero>(ladosTablero);
		LadoTablero lado;
		while (!ladosOrdenados.peek().getJugador().getNombre().equals(yo.getNombre())) {
			lado = ladosOrdenados.poll();
			ladosOrdenados.add(lado);
		}
		return List.copyOf(ladosOrdenados);
	}

	default void mostrarEstadoJugadorPartida(EstadoJugadorPartida estado, JugadorLectura jugador) {
		switch (estado) {
		case PERDIO: {
			mostrarPerdedor(jugador);
			break;
		}
		case EMPATO: {
			mostrarEmpate(jugador);
			break;
		}
		case GANO: {
			mostrarGanador(jugador);
			break;
		}
		}
	}

}
