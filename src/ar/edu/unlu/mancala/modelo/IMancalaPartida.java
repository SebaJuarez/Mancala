package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IMancalaPartida extends IObservableRemoto{

	void conectarJugador(Jugador jugador) throws RemoteException;

	void iniciarPartida() throws RemoteException;

	void mover(int indice, Jugador jugador) throws RemoteException;

	void termino() throws RemoteException;

	int obtenerClaveDeJugador(Jugador jugador) throws RemoteException;

	Jugador obtenerGanador() throws RemoteException;

	void cambiarTurno() throws RemoteException;

	void setPartidaTerminada(boolean partidaTerminada) throws RemoteException;

	void verificarCredenciales(String nombre, String contrasenia) throws RemoteException;

	void agregarJugador(String nombre, String contrasenia) throws RemoteException;

	void actualizarJugadores(Jugador jugador1, Jugador jugador2) throws RemoteException;

	List<Jugador> getJugadores() throws RemoteException;

	List<Jugador> getJugadoresConectados() throws RemoteException;

	TableroLectura getTablero() throws RemoteException;

	void setTablero(Tablero tablero) throws RemoteException;

	MoveValidator getMoveValidator() throws RemoteException;

	void setMoveValidator(MoveValidator movValidator) throws RemoteException;

	Map<Integer, Jugador> getJugadoresEnJuego() throws RemoteException;

	void setJugadoresEnJuego(Map<Integer, Jugador> jugadores) throws RemoteException;

	int getTurnoActual() throws RemoteException;

	void setTurnoActual(int turnoActual) throws RemoteException;

	boolean isPartidaTerminada() throws RemoteException;

	Jugador getUltimoEnMover() throws RemoteException;

	void desconectar(Jugador jugador, IControladorRemoto controlador) throws RemoteException;

	List<JugadorLectura> getTop(int limite) throws RemoteException;
	
	Jugador getJugador(Jugador jugador) throws RemoteException;

}