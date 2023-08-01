package ar.edu.unlu.mancala.vista.consola;

public enum OpcionesMenuPrincipalConsola implements Banner {
	NULO(""),
    COMENZAR_PARTIDA ("1) Buscar partida"),
    TOP_GANADORES ("2) Top ganadores"),
    REGLAS("3) Mostrar reglas del juego"),
    ESTADISTICAS("4) Mis estadisticas"),
    SALIR ("5) Salir");
	
    public final String label;

    private OpcionesMenuPrincipalConsola(String label) {
        this.label = label;
    }

    public static String valueOf(int opcion) {
        return OpcionesMenuPrincipalConsola.values()[opcion].label;
    }

    
    public static String mostrarOpcionesMenuPrincipal() {
    	String menu = "";
    	menu = LOGO + "\n";
    	menu = menu + "#   *                           *         *                *  # \n";
    	menu = menu + "#   *          *    "+valueOf(1)+"     *                   # \n";
    	menu = menu + "#            *      "+valueOf(2)+"                          # \n";
		menu = menu + "#      *            "+valueOf(3)+"               # \n";
    	menu = menu + "#     *             "+valueOf(4)+"         *       *     # \n";
    	menu = menu + "#     *             "+valueOf(5)+"                     *          * # \n";
    	menu = menu + "#   *               *          *    *              *          # \n";
    	menu = menu + "###############################################################";
    	return menu;
    }
}
