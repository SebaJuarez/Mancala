package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.partida.jugador.EstadoJugadorPartida;

public interface IMancalaPartida extends IMancalaComunes {

	public Jugador ultimoJugadorEnPartida() throws RemoteException;

	public String getUltimoEnMover() throws RemoteException;

	public boolean isJugadorEnPartida(Jugador jugador) throws RemoteException;

	boolean isJugadorUltimoEnMover(Jugador jugador) throws RemoteException;

	List<LadoTablero> getTablero() throws RemoteException;

	List<Jugador> obtenerGanador() throws RemoteException;

	boolean isPartidaLLena() throws RemoteException;

	TipoPartida tipoPartidaComenzada() throws RemoteException;

	Jugador turnoActual() throws RemoteException;

	EstadoJugadorPartida estadoJugadorPartida(Jugador jugador, EstadoPartida partidaTerminada) throws RemoteException;

	void iniciarPartida(Jugador jugador, TipoPartida tipoPartida) throws RemoteException;

	void mover(int indice, Jugador jugador) throws RemoteException;

}
