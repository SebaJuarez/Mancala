package ar.edu.unlu.mancala.serializacion.services;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.serializacion.Serializador;

public class JugadorServiceImpl implements JugadorService {
	
	public final static File jugadoresPath = new File("jugadores.dat");
	private Serializador serializadorJugadores = new Serializador(jugadoresPath.getAbsolutePath());
	
	@Override
	public boolean existeJugadoresFile() {
		return jugadoresPath.exists();
	}
	
	@Override
	public void guardar(List<Jugador> jugadores) {
		if(jugadores.size() >= 1) {
			serializadorJugadores.writeOneObject(jugadores.get(0));
			jugadores.forEach((jugador) -> {
				//estaba un getFirst
				if(jugadores.get(0) != jugador){
					serializadorJugadores.addOneObject(jugador);					
				}
			});
		}
	}
	
	@Override
	public List<Jugador> obtenerJugadores(){
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		Object[] recuperado = serializadorJugadores.readObjects();
		for(Object jugador : recuperado) {
			jugadores.add((Jugador)jugador);
		}
		return jugadores;
	}

}
