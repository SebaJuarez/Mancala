package ar.edu.unlu.mancala.serializacion.services;

import java.io.File;
import java.util.LinkedList;
import ar.edu.unlu.mancala.modelo.Jugador;

public class AdministradorDeSerializacion {
	
	private final File jugadoresPath = new File("jugadores.dat");
	private Serializador serializadorJugadores = new Serializador(jugadoresPath.getAbsolutePath());
	
	public boolean existeJugadoresFile() {
		return jugadoresPath.exists();
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
	
	public LinkedList<Jugador> obtenerJugadores(){
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		Object[] recuperado = serializadorJugadores.readObjects();
		for(Object jugador : recuperado) {
			jugadores.add((Jugador)jugador);
		}
		return jugadores;
	}

}
