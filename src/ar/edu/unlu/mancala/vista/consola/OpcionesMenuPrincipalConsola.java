package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuPrincipalConsola implements Colores, Banner {
	NULO(""),
	REGLAS("1) Mostrar reglas del juego"),
	ACCEDERALMENUPRINCIPAL("2) Ir al menu de inicio");

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
	
	
}
