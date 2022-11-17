package ar.edu.unlu.mancala.serializacion.services;

import java.io.File;
import java.util.LinkedList;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Partida;

public class AdministradorDeSerializacion {
	
	private final File jugadoresPath = new File("jugadores.dat");
	private final File partidaPath = new File("partida.dat");
	private Serializador serializadorJugadores = new Serializador(jugadoresPath.getAbsolutePath());
	private Serializador serializadorPartida = new Serializador(partidaPath.getAbsolutePath());
	
	public boolean existeJugadoresFile() {
		return jugadoresPath.exists();
	}
	
	public boolean existePartidaFile() {
		return partidaPath.exists();
	}
	
	public void guardar(LinkedList<Jugador> jugadores) {
		if(jugadores.size() >= 1) {
			serializadorJugadores.writeOneObject(jugadores.get(0));
			jugadores.forEach((jugador) -> {
				if(jugadores.getFirst() != jugador){
					serializadorJugadores.addOneObject(jugador);					
				}
			});
		}
	}
	
	public void guardar(Partida partida) {
		serializadorPartida.writeOneObject(partida);
	}
	
	public LinkedList<Jugador> obtenerJugadores(){
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		Object[] recuperado = serializadorJugadores.readObjects();
		for(Object jugador : recuperado) {
			jugadores.add((Jugador)jugador);
		}
		return jugadores;
	}
	
	public Partida obtenerPartida(){
		Partida ultimaPartida = null ;
		Object[] recuperado = serializadorPartida.readObjects();
		for(Object partida : recuperado) {
			ultimaPartida = (Partida)partida;
		}
		return ultimaPartida;
	}
	
}
