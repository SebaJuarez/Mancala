package ar.edu.unlu.mancala;

import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.consola.VistaConsola;

public class ToDoApp {
	
	public static void main(String[] args) {
		VistaConsola vistaConsola = new VistaConsola();
		Tablero tablero = new Tablero();
		Controlador controlador =  new Controlador(tablero,vistaConsola);
		tablero.agregarObservador(controlador);
		vistaConsola.setControlador(controlador);
		vistaConsola.iniciar();
	}
	
}