package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuInicioConsola implements Colores {
		INICIO (""),
	    REGISTRAR_JUGADOR ("1) registrar jugador nuevo"),
	    COMENZAR_PARTIDA ("2) comenzar partida nueva"),
	    LISTAR_JUGADORES ("3) lista de jugadores"),
	    TOP_GANADORES ("4) top ganadores"),
	    SALIR ("5) salir");

	    public final String label;

	    private OpcionesMenuInicioConsola(String label) {
	        this.label = label;
	    }

	    public static String valueOf(int opcion) {
	        return OpcionesMenuInicioConsola.values()[opcion].label;
	    }

	    public static void mostrarOpciones() {
	    	mostrarLogo();
	        for (OpcionesMenuInicioConsola e : OpcionesMenuInicioConsola.values()) {
	            System.out.println(AMARILLO + e.label);
	        }
	    }
	    
	    public static void mostrarLogo() {
	    	System.out.print(FONDOAMARILLO + BLANCO);
	    	System.out.println("###############################################################");
	    	System.out.println("#   #     #    #    #     #  #####     #    #          #      #");
	    	System.out.println("#   ##   ##   # #   ##    # #     #   # #   #         # #     #");
	    	System.out.println("#   # # # #  #   #  # #   # #        #   #  #        #   #    #");
	    	System.out.println("#   #  #  # #     # #  #  # #       #     # #       #     #   #");
	    	System.out.println("#   #     # ####### #   # # #       ####### #       #######   #");
	    	System.out.println("#   #     # #     # #    ## #     # #     # #       #     #   #");
	    	System.out.println("#   #     # #     # #     #  #####  #     # ####### #     #   #");
	    	System.out.println("###############################################################" + RESET);
	    }
}
