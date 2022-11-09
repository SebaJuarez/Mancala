package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;

public enum Regla implements Banner{
	OBJETIVO(FONDONEGRO + "# " + AZUL + "OBJETIVO " + BLANCO + "                                                   #\r\n"
				+ "# El objetivo del juego consiste en obtener más puntos que el #\n"
				+ "# adversario mediante el traslado de las " + VIOLETA + " habas" + BLANCO + " a la zona     #\r\n"
				+ "# \"casa\" o mediante la captura de habas del rival.            #\n"),
	COMOMOVER("#"  + AZUL + " COMO MOVER LAS HABAS" + BLANCO + "                                        #\r\n"
			+ "# El jugador al que le toca mover, debe escribir              #\r\n"
			+ "# la " + VIOLETA +"posicion" + BLANCO + " que referencia el agujero del tablero del cual  #\r\n"
			+ "# quiere mover las habas. Mediante esta acción tomará" + VIOLETA +" todas" + BLANCO + "   #\r\n"
			+ "# las habas del agujero seleccionado y las colocará una por   #\r\n"
			+ "# una en los siguientes agujeros siguiendo el sentido anti    #\r\n"
			+ "# horario. En esta diseminación de las habas, la zona         #\n"
			+ "# \"casa\" del jugador se emplea también y cuando un haba       #\r\n"
			+ "# es colocada en ella, el jugador " + VIOLETA + "gana un punto" + BLANCO + ".              #\r\n"),
	COMOMOVERCONSECUTIVAMENTE("# " + AZUL + "COMO MOVER CONSECUTIVAMENTE" + BLANCO + "                                 #\r\n"
				+ "# Si la " + VIOLETA + "última haba (del movimiento actual)"+ BLANCO + " se coloca sobre   #\r\n"
				+ "# la zona \"casa\", el jugador continúa seleccionando otro      #\r\n"
				+ "# agujero que " + VIOLETA + "no esté vacío" + BLANCO + ".                                  #\n"),
	COMOCAPTURARLASHABAS("# "+ AZUL + "COMO CAPTURAR LAS HABAS" + BLANCO +"                                     #\r\n"
				+ "# Si la última haba (del movimiento actual) se coloca sobre   #\r\n"
				+ "# un "+ VIOLETA + "agujero vacío (en el lado del jugador)" + BLANCO + ", todas las        #\r\n"
				+ "# habas de la misma columna de la fila " + VIOLETA + "opuesta" + BLANCO + " son capturadas #\r\n"
				+ "# y colocadas en la zona \"casa\" del jugador.                  #\n"),
	COMOSEFINALIZALAPARTIDA("# " + AZUL + "COMO SE FINALIZA LA PARTIDA" + BLANCO + "                                 #\r\n"
			+ "# La partida termina si uno de los jugadores no puede         #\r\n"
			+ "# realizar ningún movimiento legal - "+ VIOLETA + "no hay habas en su fila" + BLANCO + ". #\r\n"
			+ "# Cuando esto ocurre, se suman " + VIOLETA + "todas" + BLANCO + " las habas que quedan     #\r\n"
			+ "# dentro de los agujeros del rival al marcador del rival.     #\r\n"
			+ "# El jugador con el mayor número de puntos es el " + VIOLETA + "ganador" + BLANCO + ".     #\n"),
	FINAL("###############################################################\n");
	
	
	public final String regla;

	Regla(String regla) {
		this.regla = regla;
	}
	
	public static void mostrarReglas() {
		System.out.println(Banner.LOGO);
		for(Regla r : values()) {
			System.out.print(r.regla);
		}
	}
	
}