package ar.edu.unlu.mancala.vista.consola;

public enum OpcionesMenuPrincipalConsola implements Banner {
	NULO(""),
    REGISTRAR_JUGADOR ("1) Registrar jugador nuevo"),
    COMENZAR_PARTIDA ("2) Buscar partida"),
    TOP_GANADORES ("3) Top ganadores"),
    SALIR ("4) Salir");
	
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
    	menu = menu + "#      *            "+valueOf(1)+"                # \n";
    	menu = menu + "#   *          *    "+valueOf(2)+"     *                  # \n";
    	menu = menu + "#            *      "+valueOf(3)+"                          # \n";
    	menu = menu + "#     *             "+valueOf(4)+"                     *          * # \n";
    	menu = menu + "#   *               *          *    *              *          # \n";
    	menu = menu + "###############################################################";
    	return menu;
    }
}
