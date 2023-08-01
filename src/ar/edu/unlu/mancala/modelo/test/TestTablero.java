package ar.edu.unlu.mancala.modelo.test;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.modelo.Tablero;

public class TestTablero {

	public static void main(String[] args) {
		
		/*MancalaPartida partida = new MancalaPartida();
		Jugador user1 = new Jugador();
		Jugador user2 = new Jugador();
		partida.conectarJugador(user1);
		System.out.println(partida.iniciarPartida());
		partida.conectarJugador(user2);
		System.out.println(partida.getJugadores().toString());
		System.out.println(partida.iniciarPartida());
		System.out.println(partida.mover(1, user1));
		mostrar(partida.getTablero());
		System.out.println(partida.mover(8, user2));
		mostrar(partida.getTablero());
		System.out.println(partida.mover(1, user1));
		mostrar(partida.getTablero());
		System.out.println(partida.mover(10, user2));
		mostrar(partida.getTablero());
		System.out.println(partida.mover(2, user1));
		mostrar(partida.getTablero());
		System.out.println(partida.getTablero().toString());*/
		/*
		mostrar(tablero);
		System.out.println(tablero.mover(5, 1)); 
		mostrar(tablero);
		System.out.println(tablero.mover(8, 2));
		mostrar(tablero);
		System.out.println(tablero.mover(1, 1));
		mostrar(tablero);
		*/
		
		Tablero tablero = new Tablero();
		System.out.println(tablero.toString());
		
	}

	public static void mostrar(Tablero tablero) {
		System.out.println("tablero");
		for(int i = 0; i < tablero.getAgujeros().length; i++) {
			System.out.println(tablero.getAgujeros()[i].toString());
		}
		System.out.println("movimiento");
	}
}
