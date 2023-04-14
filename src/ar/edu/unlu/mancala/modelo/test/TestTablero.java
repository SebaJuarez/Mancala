package ar.edu.unlu.mancala.modelo.test;

import ar.edu.unlu.mancala.modelo.Tablero;

public class TestTablero {

	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		mostrar(tablero);
		System.out.println(tablero.mover(5, 1)); 
		mostrar(tablero);
		System.out.println(tablero.mover(8, 2));
		mostrar(tablero);
		System.out.println(tablero.mover(1, 1));
		mostrar(tablero);
		
	}

	public static void mostrar(Tablero tablero) {
		System.out.println("tablero");
		for(int i = 0; i < tablero.getAgujeros().length; i++) {
			System.out.println(tablero.getAgujeros()[i].toString());
		}
		System.out.println("movimeitno");
	}
}
