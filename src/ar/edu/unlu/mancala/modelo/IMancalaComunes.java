package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IMancalaComunes extends IObservableRemoto {

	Jugador getJugador(String jugador) throws RemoteException;

}
