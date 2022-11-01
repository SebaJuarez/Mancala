package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Banner;
import ar.edu.unlu.mancala.commons.Coloreable;

public enum OpcionesMenuInicioConsola implements Banner {
		NULO(""),
	    REGISTRAR_JUGADOR (AZUL + "1) Registrar jugador nuevo" + BLANCO),
	    COMENZAR_PARTIDA (VERDE +"2) Comenzar partida nueva" + BLANCO),
	    LISTAR_JUGADORES (BLANCO +"3) Lista de jugadores" + BLANCO),
	    TOP_GANADORES (AMARILLO +"4) Top ganadores" + BLANCO),
	    SALIR (ROJO + "5) Salir" + BLANCO);

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
	    	System.out.println("#      *            "+valueOf(1)+"                #");
	    	System.out.println("#   *          *    "+valueOf(2)+"     *           #");
	    	System.out.println("#          *        "+valueOf(3)+"             *       #");
	    	System.out.println("#            *      "+valueOf(4)+"                          #");
	    	System.out.println("#     *             "+valueOf(5)+"                     *          * #");
	    	System.out.println("#   *               *          *    *              *          #");
	    	System.out.println("###############################################################");
	    	System.out.print(RESET);
	    }
	    
	    public static void mostrarDespedida() {
	    	System.out.println(DESPEDIDA);
	    }
	    
}
