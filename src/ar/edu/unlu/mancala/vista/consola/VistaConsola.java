package ar.edu.unlu.mancala.vista.consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;

public class VistaConsola extends JFrame implements Ivista {

    private static final long serialVersionUID = 1L;
    private JTextField campoTexto;
    private JTextArea pantalla;
    private MancalaController controlador;
    private EstadosFlujo estadoFlujo = EstadosFlujo.LOG_IN;
    private JPanel panelComandos;
    private boolean esperandoTecla = false;
    private String nombreIntento;

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
        campoTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona ENTER en el campo de texto, se agrega el comando a la pantalla
                String entrada = campoTexto.getText();
                println(entrada);
                campoTexto.setText("");
            	switch(estadoFlujo) {
            	case LOG_IN : 
            		if(!esperandoTecla) {
        				opcionesMenuInicioSesion(entrada);        			
            		} else {
            			esperandoTecla = false;
            		}
            		break;
            	case LOG_IN_ENTRADA:
            		formularioUsuario(entrada);
            		break;
            	case LOG_IN_CREACION:
            		formularioCreacionUsuario(entrada);
            	case MENU_PRINCIPAL : 
            		if(!esperandoTecla) {
        				opcionesPrincipal(entrada);        			
            		} else {
            			mostrarMenuPrincipal();
            			esperandoTecla = false;
            		}
            		break;
            	case MOVIMIENTOS :
            		mover(entrada);
            		break;
            	case SALIDA:
            		if(esperandoTecla) {
            			cerrarJuego();
            		}
            		break;
				default:
					break;
            	}
            }
        });
        
        /*
        // Creación del botón para enviar los comandos
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Consolas", Font.PLAIN, 12));
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona el botón, se agrega el comando a la pantalla
                String entrada = campoTexto.getText();
                pantalla.append("$ " + entrada + "\n");
                campoTexto.setText("");
            }
        });
        */
        
        // accion que sucede cuando se cierra una ventana (una vista)
        this.addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Cerrando ventana...");
            }});
        
        // Creación del panel para el campo de texto y el botón
        panelComandos = new JPanel();
        panelComandos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelComandos.add(campoTexto);
        //panelComandos.add(botonEnviar);
        
        // Agregamos los componentes a la ventana
        getContentPane().add(scrollPantalla, BorderLayout.CENTER);
        getContentPane().add(panelComandos, BorderLayout.SOUTH);
    }

    // menu inicio de sesion ---------------------------------------------------------------
	private void opcionesMenuInicioSesion(String entrada) {
		switch(entrada) {
		case "1" :
			clearScreen();
			estadoFlujo = EstadosFlujo.LOG_IN_ENTRADA;
			println("ingrese su nombre de usuario y separado la contraseña");
			break;
		case "2" : 
			clearScreen();
			estadoFlujo = EstadosFlujo.LOG_IN_CREACION; 
			println("ingrese su nombre de usuario y separado la contraseña");
			break;
		default :
			println("ingrese una opcion correcta !");    
			break;
		}
	}
	
	private void formularioUsuario(String entry) {
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
	
	private void formularioCreacionUsuario(String entry) {
		String[] entryFiltrada = entry.trim().split(" ");
		if(entryFiltrada.length == 2) {
			String nombre = entryFiltrada[0];
			String contrasenia = entryFiltrada[1];
			nombreIntento = nombre;
			controlador.agregarJugador(nombre, contrasenia);
			//controlador.iniciarSesion(nombre, contrasenia);	
		} else {
			informar("ingreso mal las credenciales, intente de nuevo");
		}
	}
	
	//----------------------------------------------------------------------------
	
	// mwnu principal ------------------------------------------------------------
	
	private void opcionesPrincipal(String entrada) {
		switch(entrada) {
		case "1":
			controlador.jugar();
			estadoFlujo = EstadosFlujo.ESPERA;
			break;
		case "2" :
			mostrarTop(controlador.getJugadoresTop());
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "3" :
			mostrarReglas();
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "4" :
			mostrarEstadisticas();
			println("Ingrese cualquier tecla para volver al menu principal.");
			esperandoTecla = true;
			break;
		case "5" :
			clearScreen();
			println(Banner.DESPEDIDA);
			this.estadoFlujo = EstadosFlujo.SALIDA;
			println("Ingrese cualquier tecla para cerrar el juego.");
			esperandoTecla = true;
			break;
		default :
			
			break;
		}
	}
	@Override
	public void mostrarEstadisticas() {
		clearScreen();
		println(Banner.ESTADISTICA);
		JugadorLectura jugador = controlador.getJugador();
		int ganadas = jugador.getGanadas();
		int empatadas = jugador.getEmpatadas();
		int perdidas = jugador.getPerdidas();
		int jugadas = ganadas+perdidas+empatadas;
		double winRate = (ganadas > 0) ? ((ganadas / jugadas) * 100) : 0;
		double loseRate = (perdidas > 0) ? ((perdidas / jugadas) * 100) : 0;
		double drawRate = (empatadas > 0) ? ((empatadas / jugadas) * 100) : 0;
		println("Ganadas : " + jugador.getGanadas() + "  <<");
		println("");
		println("Perdidas : " + jugador.getPerdidas() + "  <<");
		println("");
		println("Empatadas : " + jugador.getEmpatadas() + "  <<");
		println("");
		println("Win Rate : " + winRate + "     Lose Rate : " + loseRate + "     Draw Rate : " + drawRate);
		println("");
	}

	private void cerrarJuego() {
		controlador.desconectar();
		this.dispose();
	}

	//----------------------------------------------------------------------------

	// movimiento ---------------------------------------------------------------
	private void mover(String entrada) {
		int pos = -1;
		try {
			pos = Integer.parseInt(entrada);
			//clearScreen();
			controlador.mover(pos);
		} catch (NumberFormatException e) {
			println("ingrese un numero!");
		}
	}
	//---------------------------------------------------------------------------
	
	// reemplazo el println -----------------------------------------------------
	private void println(String texto) {
    	pantalla.append("" + texto + "\n");
    	pantalla.setCaretPosition(pantalla.getDocument().getLength());
    }
	
	private void clearScreen() {
		pantalla.setText("");
	}
	//---------------------------------------------------------------------------
  
	// metodos de la interfaz Ivista ---------------------------------------------
	@Override
	public void iniciar() {
		// Mostramos la ventana
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		mostrarMenuInicioSesion();
	}
	
	@Override
    public void mostrarMenuInicioSesion() {
    	clearScreen();
    	println(OpcionesInicioSesion.mostrarOpcionesInicioSesion());
	}

	@Override
	public void mostrarPartida(TableroLectura tablero, JugadorLectura jugador) {
		if(this.estadoFlujo != EstadosFlujo.MOVIMIENTOS) {
			clearScreen();
			informar("comienza la partida!!");
		} 	
		pantalla.append(tablero.toString() + "\n");
		informar(jugador, "Le toca al jugador: ");
		this.estadoFlujo = EstadosFlujo.MOVIMIENTOS;
	}

	@Override
	public void informar(JugadorLectura jugador, String string) {
		informar(string + jugador.getNombre());
	}
	
	@Override
	public void informar(String string) {
		pantalla.append("Mancala: "+ string + "\n");
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
		for(int i = 0; i < topTen.size(); i++) {
			println("");
			println(">> " + (i+1) + "°) " + topTen.get(i).getNombre() + "    Ganadas : " + topTen.get(i).getGanadas() 
			+ "    Perdidas : " + topTen.get(i).getPerdidas() + "    Empatadas : " +  topTen.get(i).getEmpatadas() );
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
	//---------------------------------------------------------------------------

	// Setters y Getters --------------------------------------------------------
	@Override
	public MancalaController getControlador() {
		return controlador;
	}
	
	@Override
	public void setControlador(MancalaController controlador) {
		this.controlador = controlador;
	}
	//---------------------------------------------------------------------------



}

