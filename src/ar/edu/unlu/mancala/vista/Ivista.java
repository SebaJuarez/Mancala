package ar.edu.unlu.mancala.vista;

import java.util.List;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.LadoTablero;

public interface Ivista {

	void iniciar();

	MancalaController getControlador();

	void setControlador(MancalaController controlador);

	void informar(String string);

	void informar(JugadorLectura modelo, String string);

	void mostrarGanador(JugadorLectura obtenerGanador);

	void mostrarMenuInicioSesion();

	void mostrarEstadisticas();

	void mostrarPartida(List<LadoTablero> ladosTablero, JugadorLectura jugadorMueve);

	void mostrarSalaDeEspera();

	void mostrarMenuPrincipal();

	void mostrarReglas();

	void mostrarTop(List<JugadorLectura> topTen);

	void mostrarPerdedor(JugadorLectura jugador);

	void mostrarEmpate(JugadorLectura jugador);

	String getNombreIntento();
}