package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.LinkedList;

public class TableroBuilder implements Serializable{

	private static final long serialVersionUID = 1L;
	private TableroConfig configuracionTablero;
	
	public Tablero build() {
		
		Tablero tablero = new Tablero();
		
		// por cada lado configurado
		for(int i = 1; i <= configuracionTablero.getCantLados(); i++) {
			LadoTablero lado = new LadoTablero();
			var agujeros = new LinkedList<Agujero>();
			// por la cantidad de agujeros configurado
			for(int j = 1; j <= configuracionTablero.getCantAgujerosPorLado(); j++){
				// seteo la casa en su posicion
				if(j == configuracionTablero.getPosCasaPorLado()) {
					Casa casa = new Casa();
					agujeros.add(casa);
				} else {
					Hoyo hoyo = new Hoyo();
					agujeros.add(hoyo);
				}
			}
			// agrego agujeros al lado del tablero
			lado.setAgujeros(agujeros);
			// agrego el lado del tablero al tablero
			tablero.agregarLado(lado);
		}
		// inicializo agujeros con las habas correspondientes
		tablero.inicializarLados(configuracionTablero.getCantHabasIniciales());
		return tablero;
	}

	public TableroConfig getConfiguracionTablero() {
		return configuracionTablero;
	}

	public void setConfiguracionTablero(TableroConfig configuracionTablero) {
		this.configuracionTablero = configuracionTablero;
	}
}
