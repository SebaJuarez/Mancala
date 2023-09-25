package ar.edu.unlu.mancala.vista.consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;

public class VistaConsola extends JFrame implements Ivista {

	private static final long serialVersionUID = 1L;
	private JTextField campoTexto;
	private JTextArea pantalla;
	private MancalaController controlador;
	private EstadosFlujo estadoFlujo;
	private JPanel panelComandos;
	private boolean esperandoTecla = false;
	private String nombreIntento;
	private TableroVistaConsola tableroVistaConsola = new TableroVistaConsola();

	public VistaConsola() {
		// Configuración de la ventana principal
		setTitle("Consola");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));

		// Creación de la pantalla de la consola
		pantalla = new JTextArea();
		pantalla.setEditable(false);
		pantalla.setBackground(Color.BLACK);
		pantalla.setForeground(Color.WHITE);
		pantalla.setFont(new Font("Consolas", Font.PLAIN, 12));
		JScrollPane scrollPantalla = new JScrollPane(pantalla);
		scrollPantalla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Creación del campo de texto para escribir los comandos
		campoTexto = new JTextField(50);
		campoTexto.setToolTipText("");
		campoTexto.hasFocus();
		campoTexto.setFont(new Font("Consolas", Font.PLAIN, 12));

		campoTexto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (campoTexto.getText().length() >= 20) {
					e.consume();
				}
			}
		});

		campoTexto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando se presiona ENTER en el campo de texto, se agrega el comando a la
				// pantalla
				String entrada = campoTexto.getText();
				println(entrada);
				campoTexto.setText("");
				switch (estadoFlujo) {
				case LOG_IN:
					if (!esperandoTecla) {
						opcionesMenuInicioSesion(entrada);
					} else {
						esperandoTecla = false;
					}
					break;
				case LOG_IN_ENTRADA:
					if (entrada.toLowerCase().equals("q")) {
						mostrarMenuInicioSesion();
					}
					try {
						formularioUsuario(entrada);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				case LOG_IN_CREACION:
					if (entrada.toLowerCase().equals("q")) {
						mostrarMenuInicioSesion();
					}
					try {
						formularioCreacionUsuario(entrada);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				case MENU_PRINCIPAL:
					if (!esperandoTecla) {
						try {
							opcionesPrincipal(entrada);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					} else {
						mostrarMenuPrincipal();
						esperandoTecla = false;
					}
					break;
				case MOVIMIENTOS:
					try {
						mover(entrada);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				case SALIDA:
					if (esperandoTecla) {
						try {
							cerrarJuego();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
					break;
				default:
					break;
				}
			}
		});

		// accion que sucede cuando se cierra una ventana (una vista)
		this.addWindowListener((WindowListener) new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					cerrarJuego();
				} catch (RemoteException e1) {
					e1.printStackTrace();
					System.out.println("algo paso, no se que");
				}
			}
		});

		// Creación del panel para el campo de texto y el botón
		panelComandos = new JPanel();
		panelComandos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelComandos.add(campoTexto);
		// panelComandos.add(botonEnviar);

		// Agregamos los componentes a la ventana
		getContentPane().add(scrollPantalla, BorderLayout.CENTER);
		getContentPane().add(panelComandos, BorderLayout.SOUTH);
	}

	// menu inicio de sesion
	// ---------------------------------------------------------------
	private void opcionesMenuInicioSesion(String entrada) {
		switch (entrada) {
		case "1":
			clearScreen();
			estadoFlujo = EstadosFlujo.LOG_IN_ENTRADA;
			println("ingrese su nombre de usuario y separado la contraseña. Q para volver");
			break;
		case "2":
			clearScreen();
			estadoFlujo = EstadosFlujo.LOG_IN_CREACION;
			println("ingrese su nombre de usuario y separado la contraseña. Q para volver");
			break;
		case "3":
			clearScreen();
			println(Banner.DESPEDIDA);
			this.estadoFlujo = EstadosFlujo.SALIDA;
			println("Ingrese cualquier tecla para cerrar el juego.");
			esperandoTecla = true;
			break;
		default:
			println("ingrese una opcion correcta !");
			break;
		}
	}

	private void formularioUsuario(String entry) throws RemoteException {
		String[] entryFiltrada = entry.trim().split(" ");
		if (entryFiltrada.length == 2) {
			String nombre = entryFiltrada[0];
			String contrasenia = entryFiltrada[1];
			nombreIntento = nombre;
			controlador.iniciarSesion(nombre, contrasenia);
		} else {
			informar("ingreso mal los parametros de entrada!!");
		}
	}

	private void formularioCreacionUsuario(String entry) throws RemoteException {
		String[] entryFiltrada = entry.trim().split(" ");
		if (entryFiltrada.length == 2) {
			String nombre = entryFiltrada[0];
			String contrasenia = entryFiltrada[1];
			nombreIntento = nombre;
			// Usuario no puede estar vacío y debe tener menos de 8 caracteres
			if (nombre.isEmpty() || nombre.length() > 8 || nombre.length() < 4) {
				informar("El usuario debe tener entre 4 y 8 caracteres.");
				return;
			}
			// Contraseña no puede estar vacía y debe tener menos de 15 caracteres
			if (contrasenia.isEmpty() || contrasenia.length() > 15 || contrasenia.length() < 5) {
				informar("La contraseña debe tener entre 5 y 15 caracteres.");
				return;
			}
			// Usuario y contraseña no pueden contener espacios
			if (nombre.contains(" ") || contrasenia.contains(" ")) {
				informar("El usuario y la contraseña no pueden contener espacios.");
				return;
			}
			controlador.agregarJugador(nombre, contrasenia);
		} else {
			informar("ingreso mal las credenciales, intente de nuevo");
		}
	}

	// ----------------------------------------------------------------------------

	// mwnu principal ------------------------------------------------------------

	private void opcionesPrincipal(String entrada) throws RemoteException {
		switch (entrada) {
		case "1":
			controlador.jugar();
			break;
		case "2":
			mostrarTop(controlador.getJugadoresTop());
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "3":
			mostrarReglas();
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "4":
			mostrarEstadisticas();
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "5":
			clearScreen();
			println(Banner.DESPEDIDA);
			this.estadoFlujo = EstadosFlujo.SALIDA;
			println("Ingrese cualquier tecla para cerrar el juego.");
			esperandoTecla = true;
			break;
		default:

			break;
		}
	}

	private void cerrarJuego() throws RemoteException {
		controlador.desconectar();
		this.dispose();
	}

	// ----------------------------------------------------------------------------

	// movimiento ---------------------------------------------------------------
	private void mover(String entrada) throws RemoteException {
		int pos = -1;
		try {
			pos = Integer.parseInt(entrada);
			controlador.mover(pos);
		} catch (NumberFormatException e) {
			println("ingrese un numero!");
		}
	}
	// ---------------------------------------------------------------------------

	// reemplazo el println -----------------------------------------------------
	private void println(String texto) {
		pantalla.append("" + texto + "\n");
		pantalla.setCaretPosition(pantalla.getDocument().getLength()); // Mueve el caret al final del texto
	}

	private void clearScreen() {
		pantalla.setText("");
	}
	// ---------------------------------------------------------------------------

	// metodos de la interfaz Ivista ---------------------------------------------
	@Override
	public void iniciar() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		mostrarMenuInicioSesion();
	}

	@Override
	public void mostrarMenuInicioSesion() {
		clearScreen();
		this.estadoFlujo = EstadosFlujo.LOG_IN;
		println(OpcionesInicioSesion.mostrarOpcionesInicioSesion());
	}

	@Override
	public void informar(JugadorLectura jugador, String string) {
		informar(string + jugador.getNombre());
	}

	@Override
	public void informar(String string) {
		pantalla.append("Mancala: " + string + "\n");
	}

	@Override
	public void mostrarSalaDeEspera() {
		clearScreen();
		estadoFlujo = EstadosFlujo.ESPERA;
		println(Banner.esperandoJugador);
	}

	@Override
	public void mostrarMenuPrincipal() {
		clearScreen();
		estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
		println(OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal());
	}

	@Override
	public void mostrarPerdedor(JugadorLectura jugador) {
		this.estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
		println(Banner.jugadorLose);
		println("Estadisticas actualizadas..");
		println("Ganadas : " + jugador.getGanadas() + "<< (-) ");
		println("Perdidas : " + jugador.getPerdidas() + "<< (+1)");
		println("Empatadas : " + jugador.getEmpatadas() + "<< (-)");
		println("Ingrese cualquier tecla para volver al menu principal.");
		esperandoTecla = true;
	}

	@Override
	public void mostrarEmpate(JugadorLectura jugador) {
		this.estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
		println(Banner.empate);
		println("Estadisticas actualizadas..");
		println("Ganadas : " + jugador.getGanadas() + "<< (-) ");
		println("Perdidas : " + jugador.getPerdidas() + "<< (-)");
		println("Empatadas : " + jugador.getEmpatadas() + "<< (+1)");
		println("Ingrese cualquier tecla para volver al menu principal.");
		esperandoTecla = true;
	}

	@Override
	public void mostrarGanador(JugadorLectura ganador) {
		this.estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
		println(Banner.jugadorWin);
		println("Estadisticas actualizadas..");
		println("Ganadas : " + ganador.getGanadas() + "<< (+ 1) ");
		println("Perdidas : " + ganador.getPerdidas() + "<< (-)");
		println("Empatadas : " + ganador.getEmpatadas() + "<< (-)");
		println("Ingrese cualquier tecla para volver al menu principal.");
		esperandoTecla = true;
	}

	@Override
	public void mostrarTop(List<JugadorLectura> topTen) {
		clearScreen();
		println(Banner.TOPTEN);
		for (int i = 0; i < topTen.size(); i++) {
			println("");
			println(">> " + (i + 1) + "°) " + topTen.get(i).getNombre() + "    Ganadas : " + topTen.get(i).getGanadas()
					+ "    Perdidas : " + topTen.get(i).getPerdidas() + "    Empatadas : "
					+ topTen.get(i).getEmpatadas());
			println("");
		}
	}

	@Override
	public String getNombreIntento() {
		return this.nombreIntento;
	}

	@Override
	public void mostrarReglas() {
		clearScreen();
		println(Reglamento.mostrarReglas());
	}

	@Override
	public void mostrarEstadisticas() {
		clearScreen();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		println(Banner.ESTADISTICA);
		JugadorLectura jugador = null;
		try {
			jugador = controlador.getJugador();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		println("Ganadas : " + jugador.getGanadas() + "  <<");
		println("");
		println("Perdidas : " + jugador.getPerdidas() + "  <<");
		println("");
		println("Empatadas : " + jugador.getEmpatadas() + "  <<");
		println("");
		println("Win Rate : " + decimalFormat.format(jugador.winRate()) + "%     Lose Rate : "
				+ decimalFormat.format(jugador.loseRate()) + "%     Draw Rate : "
				+ decimalFormat.format(jugador.drawnRate()) + "%");
		println("");
	}
	// ---------------------------------------------------------------------------

	// Setters y Getters --------------------------------------------------------
	@Override
	public MancalaController getControlador() {
		return controlador;
	}

	@Override
	public void setControlador(MancalaController controlador) {
		this.controlador = controlador;
	}
	// ---------------------------------------------------------------------------

	@Override
	public void mostrarPartida(List<LadoTablero> ladosTablero, JugadorLectura jugadorMueve, TipoPartida tipoPartida,
			JugadorLectura yo) {
		if (this.estadoFlujo != EstadosFlujo.MOVIMIENTOS) {
			clearScreen();
			informar("comienza la partida!!");
		}
		pantalla.append(tableroVistaConsola.mostrarTablero(ordenarLados(ladosTablero, yo), tipoPartida) + "\n");
		informar(jugadorMueve, "Le toca al jugador: ");
		pantalla.setCaretPosition(pantalla.getDocument().getLength());
		this.estadoFlujo = EstadosFlujo.MOVIMIENTOS;
	}

}
