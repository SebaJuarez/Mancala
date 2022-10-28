package ar.edu.unlu.mancala.vista;

import ar.edu.unlu.mancala.commons.Colores;

public enum OpcionesMenuPrincipalConsola implements Colores {
	NULO(""),
	REGLAS("1) Mostrar reglas del juego"),
	ACCEDERALMENUPRINCIPAL("2) Ir al menu de inicio");

    public final String label;	
    
	OpcionesMenuPrincipalConsola(String label) {
		this.label = label;
	}
	
	 public static void mostrarOpcionesMenuPrincipal() {
		 OpcionesMenuInicioConsola.mostrarLogo();
	    	System.out.print(FONDONEGRO);
	    	System.out.println("#   *                           *         *                *  #");
	    	System.out.println("#      *           " + OpcionesMenuPrincipalConsola.REGLAS.label + "                #");
	    	System.out.println("#   *          *   " + OpcionesMenuPrincipalConsola.ACCEDERALMENUPRINCIPAL.label + "     *              #");
	    	System.out.println("#   *               *          *    *              *          #");
	    	System.out.println("###############################################################");
	    }
	
	
}
