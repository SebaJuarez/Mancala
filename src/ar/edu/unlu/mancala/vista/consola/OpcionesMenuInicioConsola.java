package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuInicioConsola implements Colores, Banner {
		NULO(""),
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
 
	    
	    public static void mostrarOpcionesMenuInicio() {
	    	System.out.println(MOSTRARLOGO);
	    	System.out.print(FONDONEGRO + BLANCO);
	    	System.out.println("#   *                           *         *                *  #");
	    	System.out.println("#      *             "+valueOf(1)+"               #");
	    	System.out.println("#   *          *     "+valueOf(2)+"     *          #");
	    	System.out.println("#          *         "+valueOf(3)+"            *       #");
	    	System.out.println("#            *       "+valueOf(4)+"                         #");
	    	System.out.println("#     *              "+valueOf(5)+"                    *          * #");
	    	System.out.println("#   *               *          *    *              *          #");
	    	System.out.println("###############################################################");
	    	System.out.print(RESET);
	    }
	    
	    public static void mostrarDespedida() {
	    	System.out.println(DESPEDIDA);
	    }
	    
}
