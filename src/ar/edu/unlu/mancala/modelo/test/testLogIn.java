package ar.edu.unlu.mancala.modelo.test;

import java.util.List;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.security.Encriptador;
import ar.edu.unlu.mancala.serializacion.services.JugadorService;
import ar.edu.unlu.mancala.serializacion.services.JugadorServiceImpl;

public class testLogIn {

	public static void main(String[] args) {
		JugadorService service = new JugadorServiceImpl();

		Jugador jugador = new Jugador();
		Jugador jugador2 = new Jugador();
		jugador.setNombre("sebastian");
		jugador.setContrasenia(Encriptador.encriptarContrasenia("juarez"));
		jugador2.setNombre("admin");
		jugador2.setContrasenia(Encriptador.encriptarContrasenia("admin"));
		service.guardar(jugador);
		service.guardar(jugador2);
	}

}
