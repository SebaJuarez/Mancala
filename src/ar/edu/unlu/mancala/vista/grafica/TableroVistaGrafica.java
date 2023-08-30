package ar.edu.unlu.mancala.vista.grafica;

import java.util.List;

import javax.swing.JFrame;

import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;

public class TableroVistaGrafica {

	private ITablero tablero;

	public void iniciarTablero(TipoPartida tipoPartida) {
		switch (tipoPartida) {
		case PARTIDA_STANDAR:
			tablero = new TableroStandar();
			break;
		case PARTIDA_4_S_AHORARIO:
			tablero = new Tablero4J_4A();
			break;
		case PARTIDA_MODO_CAPTURA:
			tablero = new TableroStandar();
			break;
		}
	}

	public void informar(String string) {
		tablero.informar(string);
	}

	public boolean isVisible() {
		return tablero != null ? ((JFrame) tablero).isVisible() : false;
	}

	public void setListener(TableroPartidaListener vistaGrafica) {
		tablero.setListener(vistaGrafica);
	}

	public void setVisible(boolean b) {
		((JFrame) tablero).setVisible(b);
	}

	public void inicializar() {
		tablero.inicializar();
	}

	public void actualizarTablero(List<LadoTablero> ladosTablero) {
		tablero.actualizarTablero(ladosTablero);
	}

}
