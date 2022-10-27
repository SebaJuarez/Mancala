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

	public String mostrarTablero(Hoyo[] hoyos) {
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

	public void mostrarMensaje(String info , CartelAdvertencia advertencia) {
		CartelAdvertencia.mostrarMensaje(info, advertencia);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
