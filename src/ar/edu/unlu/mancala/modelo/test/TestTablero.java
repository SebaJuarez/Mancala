package ar.edu.unlu.mancala.modelo.test;

import ar.edu.unlu.mancala.modelo.Tablero;

public class TestTablero {

	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		for(int i = 0; i < tablero.getAgujeros().length; i++) {
			System.out.println(tablero.getAgujeros()[i].toString());
		}
	}

}
