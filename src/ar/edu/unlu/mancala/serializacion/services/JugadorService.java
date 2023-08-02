package ar.edu.unlu.mancala.serializacion.services;

import java.util.List;

import ar.edu.unlu.mancala.modelo.Jugador;

public interface JugadorService {

	boolean existeJugadoresFile();

	List<Jugador> obtenerJugadores();

	Jugador obtenerJugadorPorNombre(String nombre);

	void guardar(Jugador jugador);

	void guardar(List<Jugador> jugadores);

}