package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuPrincipalConsola implements Banner {
	NULO(""), 
	REGLAS(AZUL + "1) Mostrar reglas del juego" + BLANCO),
	ACCEDERALMENUPRINCIPAL(VERDE + "2) Ir al menu de inicio" + BLANCO);

	public final String label;

	OpcionesMenuPrincipalConsola(String label) {
		this.label = label;
	}

	public static String valueOf(int opcion) {
		return OpcionesMenuPrincipalConsola.values()[opcion].label;
	}

	public static void mostrarOpcionesMenuPrincipal() {
		System.out.println(MOSTRARLOGO);
		System.out.print(FONDONEGRO);
		System.out.println("#   *                           *         *                *  #");
		System.out.println("#      *           " + valueOf(1) + "                #");
		System.out.println("#   *          *   " + valueOf(2) + "     *              #");
		System.out.println("#   *               *          *    *              *          #");
		System.out.println("###############################################################");
		System.out.print(RESET);
	}
	
	public static void mostrarReglas() {
		System.out.println(reglas + FONDONEGRO);
		System.out.println(
				  "# " + AZUL + "OBJETIVO " + BLANCO + "                                                   #\r\n"
				+ "# El objetivo del juego consiste en obtener más puntos que el #\n"
				+ "# adversario mediante el traslado de las " + VIOLETA + " habas" + BLANCO + " a la zona     #\r\n"
				+ "# \"casa\" o mediante la captura de habas del rival.            #\n"
				+ "#"  + AZUL + " COMO MOVER LAS HABAS" + BLANCO + "                                        #\r\n"
				+ "# El jugador al que le toca mover, debe escribir              #\r\n"
				+ "# la " + VIOLETA +"posicion" + BLANCO + " que referencia el agujero del tablero del cual  #\r\n"
				+ "# quiere mover las habas. Mediante esta acción tomará" + VIOLETA +" todas" + BLANCO + "   #\r\n"
				+ "# las habas del agujero seleccionado y las colocará una por   #\r\n"
				+ "# una en los siguientes agujeros siguiendo el sentido anti    #\r\n"
				+ "# horario. En esta diseminación de las habas, la zona         #\n"
				+ "# \"casa\" del jugador se emplea también y cuando un haba       #\r\n"
				+ "# es colocada en ella, el jugador " + VIOLETA + "gana un punto" + BLANCO + ".              #\r\n"
				+ "# " + AZUL + "COMO MOVER CONSECUTIVAMENTE" + BLANCO + "                                 #\r\n"
				+ "# Si la " + VIOLETA + "última haba (del movimiento actual)"+ BLANCO + " se coloca sobre   #\r\n"
				+ "# la zona \"casa\", el jugador continúa seleccionando otro      #\r\n"
				+ "# agujero que " + VIOLETA + "no esté vacío" + BLANCO + ".                                  #\n"
				+ "# "+ AZUL + "COMO CAPTURAR LAS HABAS" + BLANCO +"                                     #\r\n"
				+ "# Si la última haba (del movimiento actual) se coloca sobre   #\r\n"
				+ "# un "+ VIOLETA + "agujero vacío (en el lado del jugador)" + BLANCO + ", todas las        #\r\n"
				+ "# habas de la misma columna de la fila " + VIOLETA + "opuesta" + BLANCO + " son capturadas #\r\n"
				+ "# y colocadas en la zona \"casa\" del jugador.                  #\n"
				+ "# " + AZUL + "COMO SE FINALIZA LA PARTIDA" + BLANCO + "                                 #\r\n"
				+ "# La partida termina si uno de los jugadores no puede         #\r\n"
				+ "# realizar ningún movimiento legal - "+ VIOLETA + "no hay habas en su fila" + BLANCO + ". #\r\n"
				+ "# Cuando esto ocurre, se suman " + VIOLETA + "todas" + BLANCO + " las habas que quedan     #\r\n"
				+ "# dentro de los agujeros del rival al marcador del rival.     #\r\n"
				+ "# El jugador con el mayor número de puntos es el " + VIOLETA + "ganador" + BLANCO + ".     #\n"
				+ "###############################################################"
				);
	}

}
