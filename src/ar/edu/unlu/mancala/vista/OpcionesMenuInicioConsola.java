package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuInicioConsola implements Colores {
		INICIO (0, ""),
	    REGISTRAR_JUGADOR (1, "1) registrar jugador nuevo"),
	    COMENZAR_PARTIDA (2, "2) comenzar partida nueva"),
	    LISTAR_JUGADORES (3, "3) lista de jugadores"),
	    TOP_GANADORES (4, "4) top ganadores"),
	    SALIR (5, "5) salir");
	
		final static String ESC = "\033[";
	    public final int opcion;
	    public final String label;

	    private OpcionesMenuInicioConsola(int opcion, String label) {
	        this.opcion = opcion;
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
