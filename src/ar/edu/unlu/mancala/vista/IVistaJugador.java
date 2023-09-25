package ar.edu.unlu.mancala.vista;

import java.util.List;

public interface IVistaJugador extends IVistaComunes {

	String getNombreIntento();

	void mostrarMenuPrincipal();

	void mostrarMenuInicioSesion();

	void mostrarEstadisticas();

	void mostrarTop(List<JugadorLectura> topTen);
}
