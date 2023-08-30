package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IMancala extends IObservableRemoto {

	void conectarJugador(Jugador jugador) throws RemoteException;

	void mover(int indice, Jugador jugador) throws RemoteException;

	void verificarCredenciales(String nombre, String contrasenia) throws RemoteException;

	void agregarJugador(String nombre, String contrasenia) throws RemoteException;

	void actualizarJugadores(List<Jugador> jugadores) throws RemoteException;

	List<Jugador> getJugadoresConectados() throws RemoteException;

	List<Jugador> obtenerGanador() throws RemoteException;

	void desconectar(Jugador jugador, IControladorRemoto controlador) throws RemoteException;

	List<JugadorLectura> getTop(int limite) throws RemoteException;

	Jugador getJugador(Jugador jugador) throws RemoteException;

	List<Jugador> getJugadoresEnPartida() throws RemoteException;

	Partida getPartida() throws RemoteException;

	List<LadoTablero> getTablero() throws RemoteException;

	void iniciarPartida(Jugador jugador, TipoPartida tipoPartida) throws RemoteException;

}