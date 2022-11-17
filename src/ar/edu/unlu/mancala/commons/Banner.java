package ar.edu.unlu.mancala.commons;


import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.commons.Coloreable;

public interface Banner extends Coloreable{

	public  final String LOGO = FONDONEGRO + BLANCO +
			"###############################################################\n"+
			"#   #     #    #    #     #  #####     #    #          #      #\n"+
			"#   ##   ##   # #   ##    # #     #   # #   #         # #     #\n"+
			"#   # # # #  #   #  # #   # #        #   #  #        #   #    #\n"+
			"#   #  #  # #     # #  #  # #       #     # #       #     #   #\n"+
			"#   #     # ####### #   # # #       ####### #       #######   #\n"+
			"#   #     # #     # #    ## #     # #     # #       #     #   #\n"+
			"#   #     # #     # #     #  #####  #     # ####### #     #   #\n"+
			"#                                                             #"+ 
			RESET;
	
	public  final String DESPEDIDA =  FONDONEGRO + BLANCO +
			"###############################################################\n"+
			"#                     *                           *           #\n"+
			"#        *                    *                               #\n"+
			"#                                        *                *   #\n"+
		    "# "+AZUL+"   #     #    ######  ### #######  #####   #       ##  "+BLANCO+"     #\n"+ 
	        "# "+AZUL+"        # #   #     #  #  #     # #     #  #   ###   # "+BLANCO+"  *  #\n"+
	        "# "+AZUL+"   #   #   #  #     #  #  #     # #        #   ###    #"+BLANCO+"     #\n"+
	        "# "+AZUL+"   #  #     # #     #  #  #     #  #####   #          #"+BLANCO+"     #\n"+
	        "# "+AZUL+"   #  ####### #     #  #  #     #       #  #   ###    #"+BLANCO+"     #\n"+
	        "# "+AZUL+"   #  #     # #     #  #  #     # #     #      ###   # "+BLANCO+"     #\n"+
	        "# "+AZUL+"   #  #     # ######  ### #######  #####   #    #  ##  "+BLANCO+"     #\n"+
	        "# "+AZUL+"                                               #       "+BLANCO+" *   #\n"+
	        "#          *           *                                      #\n"+
	        "#  *                                *                 *       #\n"+
	        "###############################################################"+
			RESET;
	
	public final String jugador1win = FONDONEGRO + BLANCO
			+ "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║ " + AZUL + "  ganador: jugador 1 " + BLANCO + "  ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################" + RESET;
	public final String jugador2win = FONDONEGRO + BLANCO
			+ "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║ " + ROJO + "  ganador: jugador 2 " + BLANCO + "  ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################"+ RESET; 
	
	public final String empate = FONDONEGRO + BLANCO
			+ "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║ " + AMARILLO + "       empate!       " + BLANCO + "  ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################"+ RESET;
	
	public final String reglas = FONDONEGRO + BLANCO
			+ "###############################################################\n"
			+ "#                                                             #\n"
			+ "#         ######  #######  #####  #          #     #####      #\r\n"
			+ "#         #     # #       #     # #         # #   #     #     #\r\n"
			+ "#         #     # #       #       #        #   #  #           #\r\n"
			+ "#         ######  #####   #  #### #       #     #  #####      #\r\n"
			+ "#         #   #   #       #     # #       #######       #     #\r\n"
			+ "#         #    #  #       #     # #       #     # #     #     #\r\n"
			+ "#         #     # #######  #####  ####### #     #  #####      #\n" 
			+ "#                                                             #" + RESET;
	
	public static void mostrarGanador(int jugador) {
		if(jugador == 1) System.out.println(jugador1win); 
		else if(jugador == 2) System.out.println(jugador2win);
		else System.out.println(empate);
	}
	
    public static void mostrarDespedida() {
    	System.out.println(DESPEDIDA);
    }
	
	public static void mostrarTablero(Hoyo[] tablero) {
		String tableros = FONDONEGRO + "************************************************\n";
		tableros += "*              << mancala game >>  " + AMARILLO + "S) guardar " + BLANCO + " *\n";
		tableros += "*                                              *\n";
		tableros +=  "*" + ROJO + "          L    K    J    I    H    G        " + BLANCO + "  *\n";
		tableros +=  "*" + ROJO + "   |   |" + VERDE;
		tableros += "| " + tablero[Posicion.L.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.K.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.J.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.I.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.H.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.G.ordinal()].getCantHabas() + " |";
		tableros += AZUL + "|   |   " + BLANCO + "*";
		tableros += "\n";
		tableros += "*" + ROJO + "   | " + tablero[Posicion.CASAJ2.ordinal()].getCantHabas() + " |" + VERDE + "|";
		tableros += VIOLETA +"----------------------------";
		tableros += VERDE  +  "|" + AZUL + "| " + tablero[Posicion.CASAJ1.ordinal()].getCantHabas() + " |   " + BLANCO + "*";
		tableros += "\n";
		tableros += "*" + ROJO + "   |   |" + VERDE;
		tableros += "| " + tablero[Posicion.A.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.B.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.C.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.D.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.E.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.F.ordinal()].getCantHabas() + " |";
		tableros += AZUL + "|   |   " + BLANCO + "*";
		tableros += "\n";
		tableros += "*" + AZUL + "          A    B    C    D    E    F          " + BLANCO + "*\n";
		 tableros += "*                                              *\n";
		tableros += "************************************************" + RESET;
		System.out.println(tableros);
	}
}
