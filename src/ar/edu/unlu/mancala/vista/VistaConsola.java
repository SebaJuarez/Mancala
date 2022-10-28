package ar.edu.unlu.mancala.vista;

import java.util.LinkedList;
import java.util.Scanner;

import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;

public class VistaConsola {

	private Controlador controlador;

	public void mostrarTablero(Hoyo[] hoyos) {
		String tableros = "";
		tableros += "       L    K    J    I    H    G";
		tableros += "\n";
		tableros += "|   |";
		tableros += "| " + hoyos[Posicion.L.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.K.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.J.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.I.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.H.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.G.ordinal()].getCantHabas() + " |";
		tableros += "|   |";
		tableros += "\n";
		tableros += "| " + hoyos[Posicion.CASAJ2.ordinal()].getCantHabas() + " ||";
		tableros += "----------------------------";
		tableros += "|| " + hoyos[Posicion.CASAJ1.ordinal()].getCantHabas() + " |";
		tableros += "\n";
		tableros += "|   |";
		tableros += "| " + hoyos[Posicion.A.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.B.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.C.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.D.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.E.ordinal()].getCantHabas() + " |";
		tableros += "| " + hoyos[Posicion.F.ordinal()].getCantHabas() + " |";
		tableros += "|   |";
		tableros += "\n";
		tableros += "       A    B    C    D    E    F";
		System.out.println(tableros);
	}


	public void iniciar(){
		OpcionesMenuPrincipalConsola opcion = OpcionesMenuPrincipalConsola.NULO ;
		Scanner sc = new Scanner(System.in);
		while (opcion != OpcionesMenuPrincipalConsola.ACCEDERALMENUPRINCIPAL) {
			OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal();;
			opcion = OpcionesMenuPrincipalConsola.values()[sc.nextInt()];
			switch (opcion) {
			case REGLAS:
				mostrarReglas();
				break;
			case ACCEDERALMENUPRINCIPAL:
				mostrarMenuInicio();
				break;
			default:
				break;
			}
		}
		sc.close();
	}

// reglas del juego
	private void mostrarReglas() {
		
	}

//
	private void comenzarPartida() {

	}

	private void mostrarMenuInicio(){
		OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.NULO ;
		Scanner sc = new Scanner(System.in);
		while ((OpcionesMenuInicioConsola)opcion != OpcionesMenuInicioConsola.SALIR) {
			OpcionesMenuInicioConsola.mostrarOpcionesMenuInicio();
			opcion = OpcionesMenuInicioConsola.values()[sc.nextInt()];
			switch (opcion) {
			case REGISTRAR_JUGADOR:
				registroJugador();
				break;
			case COMENZAR_PARTIDA:
				comenzarPartida();
				break;
			case LISTAR_JUGADORES:
				break;
			case TOP_GANADORES:
				break;
			default:
				break;
			}
		}
		sc.close();
	}

	private void registroJugador(){
		String nombre = "";
		Scanner sc = new Scanner(System.in);
		this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.NORMAL);
		nombre = sc.nextLine();
		while(nombre.isEmpty()) {
			this.mostrarMensaje("No se admiten nombres vacios!", CartelAdvertencia.ERROR);
			this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.ADVERTENCIA);
			nombre = sc.nextLine();
		}
		controlador.agregarJugador(nombre);
	}


	public void mostrarMensaje(String info , CartelAdvertencia advertencia){
		CartelAdvertencia.mostrarMensaje(info, advertencia);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	public void mostrarJugadores(LinkedList<Jugador> jugadores) {
		jugadores.forEach(jugador -> System.out.println(jugador.getNombre()));
	}

}
