package ar.edu.unlu.mancala.vista.consola;

import java.util.LinkedList;
import java.util.Scanner;
import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Vista;
import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;

public class VistaConsola implements Vista{

	private MancalaController controlador;
	private Scanner sc = new Scanner(System.in);

	public void mostrarTablero(Hoyo[] hoyos) {
		Banner.mostrarTablero(hoyos);
	}

	public void iniciar() {
		OpcionesMenuPrincipalConsola opcion = OpcionesMenuPrincipalConsola.NULO;
		while (opcion != OpcionesMenuPrincipalConsola.ACCEDERALMENUPRINCIPAL) {
			OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal();
			opcion = validarEntradaMenuPrincipal();
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
		Reglamento.mostrarReglas();
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
		String movs = mov.nextLine();
		if(movs.toUpperCase().equalsIgnoreCase("S")) {
			controlador.guardarDatos();
			movimientos();
		} else {
			Posicion pos = Posicion.getPosicionDeString(movs);
			while(pos == null) {
				this.mostrarMensaje("Ingrese una posicion valida!", CartelAdvertencia.ERROR);
				pos = Posicion.getPosicionDeString(mov.nextLine());
			}
			controlador.mover(pos);			
		}
	}

	public void mostrarGanador(Jugador jugador , int numeroJugador) {
		Banner.mostrarGanador(numeroJugador);
		if(jugador != null) 	
			this.mostrarJugador(jugador);
		 else 
			 this.mostrarMensaje("EMPATE!!", CartelAdvertencia.ADVERTENCIA);
		precioneEnter();
		this.mostrarMenuInicio();
	}

	private void precioneEnter() {
		this.mostrarMensaje("Presione enter para continuar", CartelAdvertencia.NORMAL);
		sc.nextLine();
	}
	
	private void mostrarMenuInicio() {
		OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.NULO;
		while (opcion != OpcionesMenuInicioConsola.SALIR) {
			OpcionesMenuInicioConsola.mostrarOpcionesMenuInicio();
			opcion = validarEntradaMenuInicio();
			switch (opcion) {
			case REGISTRAR_JUGADOR:
				registroJugador();
				break;
			case COMENZAR_PARTIDA:
				comenzarPartida();
				break;
			case CONTINUAR_PARTIDA:
				controlador.continuarPartida();
				break;
			case LISTAR_JUGADORES:
				controlador.obtenerJugadores();
				break;
			case TOP_GANADORES:
				controlador.topGanadores();
				break;
			case SALIR:
				controlador.guardarDatos();
				Banner.mostrarDespedida();				
			default:
				break;
			}
		}
	}

	private void registroJugador() {
		String nombre = "";
		this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.NORMAL);
		nombre = sc.nextLine();
		while (nombre.isEmpty()) {
			this.mostrarMensaje("No se admiten nombres vacios!", CartelAdvertencia.ERROR);
			this.mostrarMensaje("ingrese el nombre del jugador", CartelAdvertencia.ADVERTENCIA);
			nombre = sc.nextLine();
		}
		controlador.agregarJugador(nombre);
		precioneEnter();
	}

	public void mostrarMensaje(String info, CartelAdvertencia advertencia) {
		CartelAdvertencia.mostrarMensaje(info, advertencia);
	}

	public void setControlador(MancalaController controlador) {
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
	
	@SuppressWarnings("finally")
	private OpcionesMenuPrincipalConsola validarEntradaMenuPrincipal(){
		String indice = "0";
		int indice2 = 0;
		OpcionesMenuPrincipalConsola opcion = OpcionesMenuPrincipalConsola.NULO;
		indice = sc.nextLine();
		try {
			indice2 = Integer.parseInt(indice);
		} catch (Exception e) {
			this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
			indice2 = 0;
		} finally {
			try {
				opcion = OpcionesMenuPrincipalConsola.values()[indice2];
			} catch(Exception e) {
				this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
				opcion = OpcionesMenuPrincipalConsola.NULO;
			} finally {
				return  opcion;			
			}
		}
	}
	
	@SuppressWarnings("finally")
	private OpcionesMenuInicioConsola validarEntradaMenuInicio(){
		String indice = "0";
		int indice2 = 0;
		OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.NULO;
		indice = sc.nextLine();
		try {
			indice2 = Integer.parseInt(indice);
		} catch (Exception e) {
			this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
			indice2 = 0;
		} finally {
			try {
				opcion = OpcionesMenuInicioConsola.values()[indice2];
			} catch(Exception e) {
				this.mostrarMensaje("Numero de opcion invalida!!", CartelAdvertencia.ERROR);
				opcion = OpcionesMenuInicioConsola.NULO;
			} finally {
				return  opcion;			
			}
		}
	}

}
