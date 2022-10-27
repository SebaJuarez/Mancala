package ar.edu.unlu.mancala;
import java.util.LinkedList;

import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.VistaConsola;

public class Mancala {
	
	public static void main(String[] args) {
		Tablero tablero = new Tablero(4);
		VistaConsola vistaConsola = new VistaConsola();
		Controlador controlador =  new Controlador(tablero,vistaConsola);
		vistaConsola.setControlador(controlador);
		vistaConsola.iniciar();
	}
	
}