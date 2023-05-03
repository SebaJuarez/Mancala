package ar.edu.unlu.mancala.modelo.test;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.vista.consola.VistaConsola;

public class testMancala {

	public static void main(String[] args) {

		VistaConsola vista1 = new VistaConsola();
		VistaConsola vista2 = new VistaConsola();
		MancalaPartida mancalaModel = new MancalaPartida();
		MancalaController controlador1 = new MancalaController();
		MancalaController controlador2 = new MancalaController();
		controlador1.setModel(mancalaModel);
		controlador2.setModel(mancalaModel);
		vista1.setControlador(controlador1);
		vista2.setControlador(controlador2);
		controlador1.setVista(vista1);
		controlador2.setVista(vista2);
		mancalaModel.agregarObservador(controlador1);
		mancalaModel.agregarObservador(controlador2);
		vista1.iniciar();
		vista2.iniciar();
	}

}
