package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Coloreable;

public enum OpcionesMenuInicioConsola implements Banner {
		NULO(""),
	    REGISTRAR_JUGADOR (AZUL + "1) Registrar jugador nuevo" + BLANCO),
	    COMENZAR_PARTIDA (VERDE +"2) Comenzar partida nueva" + BLANCO),
	    CONTINUAR_PARTIDA (VERDE +"3) Continuar partida" + BLANCO),
	    LISTAR_JUGADORES (BLANCO +"4) Lista de jugadores" + BLANCO),
	    TOP_GANADORES (AMARILLO +"5) Top ganadores" + BLANCO),
	    SALIR (ROJO + "6) Salir" + BLANCO);

	    public final String label;

	    private OpcionesMenuInicioConsola(String label) {
	        this.label = label;
	    }

	    public static String valueOf(int opcion) {
	        return OpcionesMenuInicioConsola.values()[opcion].label;
	    }
 
	    
	    public static void mostrarOpcionesMenuInicio() {
	    	System.out.println(LOGO);
	    	System.out.print(FONDONEGRO + BLANCO);
	    	System.out.println("#   *                           *         *                *  #");
	    	System.out.println("#      *            "+valueOf(1)+"                #");
	    	System.out.println("#   *          *    "+valueOf(2)+"     *           #");
	    	System.out.println("#            *      "+valueOf(3)+"                      #");
	    	System.out.println("#            *      "+valueOf(4)+"                     #");
	    	System.out.println("#            *      "+valueOf(5)+"                          #");
	    	System.out.println("#     *             "+valueOf(6)+"                     *          * #");
	    	System.out.println("#   *               *          *    *              *          #");
	    	System.out.println("###############################################################");
	    	System.out.print(RESET);
	    }
	    
	    
}
