package ar.edu.unlu.mancala.controlador;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.vista.JugadorLectura;

public class JugadorController {
	
	private final MancalaPartida modelo;
	
	public JugadorController(MancalaPartida modelo) {
		this.modelo = modelo;
	}

	public void agregarJugador(String nombre, String contrasenia) {
        modelo.agregarJugador(nombre, contrasenia);
    }
	
    public Jugador iniciarSesion(String nombre, String contrasenia) {
        return modelo.verificarCredenciales(nombre,contrasenia);
    }
	
	// recuperado jugadores para el top
	public List<JugadorLectura> getJugadoresTop() {
		return modelo.getJugadores().stream()
				.map(j -> (JugadorLectura)j)
				.sorted(Comparator.comparing(JugadorLectura::getGanadas).reversed())
				.collect(Collectors.toList());
	}
	
}
