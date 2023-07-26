package ar.edu.unlu.mancala.serializacion.services;

import java.util.List;

import ar.edu.unlu.mancala.modelo.Jugador;

public interface JugadorService {

	boolean existeJugadoresFile();

	void guardar(List<Jugador> jugadores);

	List<Jugador> obtenerJugadores();
	
	Jugador obtenerJugadorPorNombre(String nombre);

}