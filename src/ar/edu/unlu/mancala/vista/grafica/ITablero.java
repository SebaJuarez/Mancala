package ar.edu.unlu.mancala.vista.grafica;

import java.util.List;

import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;

public interface ITablero {

	void informar(String texto);

	void actualizarTablero(List<LadoTablero> ladosTablero);

	TableroPartidaListener getListener();

	void setListener(TableroPartidaListener listener);

	void setJugadores(List<JugadorLectura> jugadoresEnPartida);

	void inicializar();

}