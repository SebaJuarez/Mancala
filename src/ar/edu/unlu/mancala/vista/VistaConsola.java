package ar.edu.unlu.mancala.vista;

import java.util.Scanner;

import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;

public class VistaConsola {

	private Controlador controlador;
	private OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.INICIO;
	private int jugadorActual = 1;

	private String mostrarTablero(Tablero observado) {
		Hoyo[] tablero = observado.getTablero();
		String tableros = "";
		tableros += "       L    K    J    I    H    G";
		tableros += "\n";
		tableros += "|   |";
		tableros += "| " + tablero[Posicion.L.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.K.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.J.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.I.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.H.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.G.ordinal()].getCantHabas() + " |";
		tableros += "|   |";
		tableros += "\n";
		tableros += "| " + tablero[Posicion.CASAJ2.ordinal()].getCantHabas() + " ||";
		tableros += "----------------------------";
		tableros += "|| " + tablero[Posicion.CASAJ1.ordinal()].getCantHabas() + " |";
		tableros += "\n";
		tableros += "|   |";
		tableros += "| " + tablero[Posicion.A.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.B.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.C.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.D.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.E.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.F.ordinal()].getCantHabas() + " |";
		tableros += "|   |";
		tableros += "\n";
		tableros += "       A    B    C    D    E    F";

		return tableros;
	}

	public void mostrarMenu() {
		OpcionesMenuInicioConsola.mostrarOpciones();
	}

	public void iniciar() {
		Scanner sc = new Scanner(System.in);
		while (opcion != OpcionesMenuInicioConsola.SALIR) {
			mostrarMenu();
			opcion = OpcionesMenuInicioConsola.values()[sc.nextInt()];
			switch (opcion) {
			case REGISTRAR_JUGADOR:
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

	private void comenzarPartida() {
		controlador.mover(Posicion.getPosicionDeString("A"), this.jugadorActual);
		controlador.mover(Posicion.getPosicionDeString("H"), this.jugadorActual);
		controlador.mover(Posicion.getPosicionDeString("L"), this.jugadorActual);
		controlador.mover(Posicion.getPosicionDeString("G"), this.jugadorActual);
		controlador.mover(Posicion.getPosicionDeString("D"), this.jugadorActual);
		controlador.mover(Posicion.getPosicionDeString("L"), this.jugadorActual);
	}

	public void mostrarMensaje(String info) {
		System.out.println(info);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
