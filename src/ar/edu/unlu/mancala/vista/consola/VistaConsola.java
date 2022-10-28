package ar.edu.unlu.mancala.vista.consola;

import java.util.LinkedList;
import java.util.Scanner;
import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;

public class VistaConsola {

	private Controlador controlador;
	private Scanner sc = new Scanner(System.in);

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


	public void iniciar() {
		OpcionesMenuPrincipalConsola opcion = OpcionesMenuPrincipalConsola.NULO;
		while (opcion != OpcionesMenuPrincipalConsola.ACCEDERALMENUPRINCIPAL) {
			OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal();
			try {
				opcion = OpcionesMenuPrincipalConsola.values()[sc.nextInt()];
			} catch (Exception e) {
				this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
			}
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
	}

	private void mostrarReglas() {

	}

	private void comenzarPartida() {
		Scanner scint = new Scanner(System.in);
		this.mostrarMensaje("Ingrese el id del jugador 1", CartelAdvertencia.NORMAL);
		int id1 = sc.nextInt();
		this.mostrarMensaje("Ingrese el id del jugador 2", CartelAdvertencia.NORMAL);
		int id2 = sc.nextInt();
		while (!controlador.comenzarJuego(id1, id2)) {
			this.mostrarMensaje("Ingrese datos validos o -1 para volver al menu", CartelAdvertencia.ERROR);
			this.mostrarMensaje("Ingrese el id del jugador 1", CartelAdvertencia.ADVERTENCIA);
			id1 = sc.nextInt();
			if (id1 == -1)
				mostrarMenuInicio();
			this.mostrarMensaje("Ingrese el id del jugador 2", CartelAdvertencia.ADVERTENCIA);
			id2 = sc.nextInt();
			if (id2 == -1)
				mostrarMenuInicio();
		}
		controlador.comenzarJuego(id1, id2);
		movimientos();
	}

	public void movimientos() {
		Scanner mov = new Scanner(System.in);
		Posicion pos = Posicion.getPosicionDeString(mov.nextLine());
		controlador.mover(pos);
	}

	public void mostrarGanador(Jugador jugador) {

	}

	private void mostrarMenuInicio() {
		OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.NULO;
		while (opcion != OpcionesMenuInicioConsola.SALIR) {
			OpcionesMenuInicioConsola.mostrarOpcionesMenuInicio();
			try {
				opcion = OpcionesMenuInicioConsola.values()[sc.nextInt()];
			} catch (Exception e) {
				this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
			}
			switch (opcion) {
			case REGISTRAR_JUGADOR:
				registroJugador();
				break;
			case COMENZAR_PARTIDA:
				comenzarPartida();
				break;
			case LISTAR_JUGADORES:
				controlador.obtenerJugadores();
				break;
			case TOP_GANADORES:
				break;
			default:
				break;
			}
		}
	}

	private void registroJugador() {
		String nombre = "";
		Scanner scline = new Scanner(System.in);
		this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.NORMAL);
		nombre = scline.nextLine();
		while (nombre.isEmpty()) {
			this.mostrarMensaje("No se admiten nombres vacios!", CartelAdvertencia.ERROR);
			this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.ADVERTENCIA);
			nombre = scline.nextLine();
		}
		controlador.agregarJugador(nombre);
		this.mostrarMensaje("Presione una tecla para continuar..", CartelAdvertencia.NORMAL);
		scline.nextLine();
	}

	public void mostrarMensaje(String info, CartelAdvertencia advertencia) {
		CartelAdvertencia.mostrarMensaje(info, advertencia);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void mostrarJugadores(LinkedList<Jugador> jugadores) {
		Scanner scline = new Scanner(System.in);
		for (Jugador jugador : jugadores) {
			this.mostrarMensaje("ID: " + jugador.getId() + "-------------------------------------------------------",
					CartelAdvertencia.NORMAL);
			this.mostrarMensaje("Nombre -> " + jugador.getNombre(), CartelAdvertencia.NORMAL);
			this.mostrarMensaje("Jugadas -> " + jugador.getPartidasJugadas(), CartelAdvertencia.ADVERTENCIA);
			this.mostrarMensaje("Ganadas -> " + jugador.getPartidasGanadas(), CartelAdvertencia.COMPLETO);
			this.mostrarMensaje("Perdidas -> " + (jugador.getPartidasJugadas() - jugador.getPartidasGanadas()),
					CartelAdvertencia.ERROR);
			this.mostrarMensaje("------------------------------------------------------------",
					CartelAdvertencia.NORMAL);
		}
		this.mostrarMensaje("Presione una tecla para continuar..", CartelAdvertencia.NORMAL);
		scline.nextLine();
	}

}
