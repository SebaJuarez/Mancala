package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

public interface IMancalaJugador extends IMancalaComunes {

	void crearJugador(String nombre, String contrasenia) throws RemoteException;

	void verificarCredenciales(String nombre, String contrasenia) throws RemoteException;

	void desconectar(Jugador jugador, IControladorRemoto controlador) throws RemoteException;

	void actualizarJugadores(List<Jugador> jugadores) throws RemoteException;

	List<JugadorLectura> getTop(int limite) throws RemoteException;

}
