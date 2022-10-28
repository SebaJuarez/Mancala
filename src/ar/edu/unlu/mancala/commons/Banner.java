package ar.edu.unlu.mancala.commons;

import ar.edu.unlu.mancala.modelo.Hoyo;
import ar.edu.unlu.mancala.modelo.Posicion;

public interface Banner extends Colores{

	public  final String MOSTRARLOGO = FONDONEGRO + BLANCO +
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
	
	public static void tablero(Hoyo[] tablero) {
		String tableros = FONDONEGRO + "                                                \n";
		tableros += ROJO + "           L    K    J    I    H    G           \n";
		tableros += ROJO + "    |   |" + VERDE;
		tableros += "| " + tablero[Posicion.L.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.K.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.J.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.I.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.H.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.G.ordinal()].getCantHabas() + " |";
		tableros += AZUL + "|   |    ";
		tableros += "\n";
		tableros += ROJO + "    | " + tablero[Posicion.CASAJ2.ordinal()].getCantHabas() + " |" + VERDE + "|";
		tableros += VIOLETA +"----------------------------";
		tableros += VERDE  +  "|" + AZUL + "| " + tablero[Posicion.CASAJ1.ordinal()].getCantHabas() + " |    ";
		tableros += "\n";
		tableros += ROJO + "    |   |" + VERDE;
		tableros += "| " + tablero[Posicion.A.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.B.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.C.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.D.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.E.ordinal()].getCantHabas() + " |";
		tableros += "| " + tablero[Posicion.F.ordinal()].getCantHabas() + " |";
		tableros += AZUL + "|   |    ";
		tableros += "\n";
		tableros += "           A    B    C    D    E    F           \n";
		tableros += "                                                \n";
		System.out.println(tableros);
	}
}
