package ar.edu.unlu.mancala.vista.consola;

public enum OpcionesMenuInicioConsola implements Banner{
	NULO(""), 
	REGLAS("1) Mostrar reglas del juego"),
	ACCEDERALMENUPRINCIPAL("2) Ir al menu de inicio");

	public final String label;

	OpcionesMenuInicioConsola(String label) {
		this.label = label;
	}

	public static String valueOf(int opcion) {
		return OpcionesMenuInicioConsola.values()[opcion].label;
	}

	public static String mostrarOpcionesMenuPrincipal() {
		String menu = "";
		menu = LOGO + "\n";
		menu = menu + "#   *                           *         *                *  # \n";
		menu = menu + "#      *           " + valueOf(1) + "                # \n";
		menu = menu + "#   *          *   " + valueOf(2) + "     *              # \n";
		menu = menu + "#   *               *          *    *              *          # \n";
		menu = menu + "############################################################### \n";
		return menu;
	}
}