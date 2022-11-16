package ar.edu.unlu.mancala;

import ar.edu.unlu.mancala.commons.Vista;
import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.consola.VistaConsola;

public class ToDoApp {
	
	public static void main(String[] args) {
		Vista vistaConsola =  new VistaConsola();
		Tablero tablero = new Tablero();
		MancalaController controlador =  new MancalaController(tablero, vistaConsola);
		tablero.agregarObservador(controlador);
		vistaConsola.setControlador(controlador);
		vistaConsola.iniciar();
	}
	
}