package ar.edu.unlu.mancala.vista.consola;

import java.util.LinkedList;
import java.util.Scanner;
import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.controlador.Controlador;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;

public class VistaConsola{

	private Controlador controlador;
	private Scanner sc = new Scanner(System.in);

	public void mostrarTablero(Hoyo[] hoyos) {
		Banner.tablero(hoyos);
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
		//OpcionesMenuPrincipalConsola.mostrarReglas();
		Regla.mostrarReglas();
		precioneEnter();
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

	}

	public void movimientos() {
		Scanner mov = new Scanner(System.in);
		Posicion pos = Posicion.getPosicionDeString(mov.nextLine());
		while(pos == null) {
			this.mostrarMensaje("Ingrese una posicion valida!", CartelAdvertencia.ERROR);
			pos = Posicion.getPosicionDeString(mov.nextLine());
		}
		controlador.mover(pos);
	}

	public void mostrarGanador(Jugador jugador , int numeroJugador) {
		Banner.ganador(numeroJugador);
		if(jugador != null) 	
			this.mostrarJugador(jugador);
		 else 
			 this.mostrarMensaje("EMPATE!!", CartelAdvertencia.ADVERTENCIA);
		precioneEnter();
		this.mostrarMenuInicio();
	}

	private void precioneEnter() {
		Scanner scenter = new Scanner(System.in);
		this.mostrarMensaje("Presione enter para continuar", CartelAdvertencia.NORMAL);
		scenter.nextLine();		
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
			case SALIR:
				OpcionesMenuInicioConsola.mostrarDespedida();				
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
		precioneEnter();
	}

	public void mostrarMensaje(String info, CartelAdvertencia advertencia) {
		CartelAdvertencia.mostrarMensaje(info, advertencia);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void mostrarJugadores(LinkedList<Jugador> jugadores) {
		for (Jugador jugador : jugadores) 
			mostrarJugador(jugador);
		precioneEnter();
	}
	
	private void mostrarJugador(Jugador jugador) {
		this.mostrarMensaje("ID: " + jugador.getId() + "-------------------------------------------------------",CartelAdvertencia.NORMAL);
		this.mostrarMensaje("Nombre -> " + jugador.getNombre(), CartelAdvertencia.NORMAL);
		this.mostrarMensaje("Jugadas -> " + jugador.getPartidasJugadas(), CartelAdvertencia.NORMAL);
		this.mostrarMensaje("Ganadas -> " + jugador.getPartidasGanadas(), CartelAdvertencia.COMPLETO);
		this.mostrarMensaje("Empatadas -> " + jugador.getPartidasEmpatadas(), CartelAdvertencia.ADVERTENCIA);
		this.mostrarMensaje("Perdidas -> " + (jugador.getPartidasJugadas() - jugador.getPartidasGanadas() - jugador.getPartidasEmpatadas()),CartelAdvertencia.ERROR);
		this.mostrarMensaje("------------------------------------------------------------",CartelAdvertencia.NORMAL);
	}

}
