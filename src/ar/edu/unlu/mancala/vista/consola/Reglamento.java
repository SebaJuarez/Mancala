package ar.edu.unlu.mancala.vista.consola;

public enum Reglamento implements Banner {
	OBJETIVO("# OBJETIVO                                                    #\r\n"
			+ "# El objetivo del juego consiste en obtener más puntos que el #\n"
			+ "# adversario mediante el traslado de las  habas a la zona     #\r\n"
			+ "# \"casa\" o mediante la captura de habas del rival.            #\n"),
	COMOMOVER("# COMO MOVER LAS HABAS                                        #\r\n"
			+ "# El jugador al que le toca mover, debe escribir              #\r\n"
			+ "# la posicion que referencia el agujero del tablero del cual  #\r\n"
			+ "# quiere mover las habas. Mediante esta acción tomará todas   #\r\n"
			+ "# las habas del agujero seleccionado y las colocará una por   #\r\n"
			+ "# una en los siguientes agujeros siguiendo el sentido anti    #\r\n"
			+ "# horario. En esta diseminación de las habas, la zona         #\n"
			+ "# \"casa\" del jugador se emplea también y cuando un haba       #\r\n"
			+ "# es colocada en ella, el jugador gana un punto.              #\r\n"),
	COMOMOVERCONSECUTIVAMENTE("# COMO MOVER CONSECUTIVAMENTE                                 #\r\n"
			+ "# Si la última haba (del movimiento actual) se coloca sobre   #\r\n"
			+ "# la zona \"casa\", el jugador continúa seleccionando otro      #\r\n"
			+ "# agujero que no esté vacío.                                  #\n"),
	COMOCAPTURARLASHABAS("# COMO CAPTURAR LAS HABAS                                     #\r\n"
			+ "# Si la última haba (del movimiento actual) se coloca sobre   #\r\n"
			+ "# un agujero vacío (en el lado del jugador), todas las        #\r\n"
			+ "# habas de la misma columna de la fila opuesta son capturadas #\r\n"
			+ "# y colocadas en la zona \"casa\" del jugador.                  #\n"),
	COMOSEFINALIZALAPARTIDA("# COMO SE FINALIZA LA PARTIDA                                 #\r\n"
			+ "# La partida termina si uno de los jugadores no puede         #\r\n"
			+ "# realizar ningún movimiento legal - no hay habas en su fila. #\r\n"
			+ "# Cuando esto ocurre, se suman todas las habas que quedan     #\r\n"
			+ "# dentro de los agujeros del rival al marcador del rival.     #\r\n"
			+ "# El jugador con el mayor número de puntos es el ganador.     #\n"),
	FINAL("###############################################################\n");

	public final String regla;

	Reglamento(String regla) {
		this.regla = regla;
	}

	public static String mostrarReglas() {
		String reglastx = "";
		reglastx = reglas + "\n";
		for (Reglamento r : values()) {
			reglastx = reglastx + r.regla + "\n";
		}
		return reglastx;
	}
}