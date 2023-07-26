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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ar.edu.unlu.mancala.controlador.JugadorController;
import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;

public class VistaConsola extends JFrame implements Ivista {

    private static final long serialVersionUID = 1L;
    private JTextField campoTexto;
    private JTextArea pantalla;
    private MancalaController controlador;
    private JugadorController controladorJugador;
    private EstadosFlujo estadoFlujo = EstadosFlujo.LOG_IN;
    private JPanel panelComandos;
    private boolean esperandoTecla = false;

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
            			menuPrincipal();
            			esperandoTecla = false;
            		}
            		break;
            	/*case LOGIN :
            		Jugador jugador = new Jugador();
            		jugador.setNombre(entrada);
            		controlador.setJugador(jugador);
            		estadoFlujo = EstadosFlujo.MOVIMIENTOS;
            		break; */
            	case MOVIMIENTOS :
            		mover(entrada);
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
		if(entryFiltrada.length == 2) {
			String nombre = entryFiltrada[0];
			String contrasenia = entryFiltrada[1];
			Jugador jugadorMain = controladorJugador.iniciarSesion(nombre, contrasenia);
			System.out.println(jugadorMain);
			if(jugadorMain != null) {
				controlador.setJugador(jugadorMain);	
				println("ingrese cualquier tecla para volver al menu");
				estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
				esperandoTecla = true;
			} else {
				informar("nombre o contraseña incorrectos !!");
			}
		} else {
			informar("ingreso mal los parametros de entrada!!");
		}
	}
	
	private void formularioCreacionUsuario(String entry) {
		String[] entryFiltrada = entry.trim().split(" ");
		if(entryFiltrada.length == 2) {
			String nombre = entryFiltrada[0];
			String contrasenia = entryFiltrada[1];
			if (controladorJugador.agregarJugador(nombre, contrasenia) == EstadoPersistencia.GUARDADO_EXITOSO) {
				Jugador jugadorMain = controladorJugador.iniciarSesion(nombre, contrasenia);
				controlador.setJugador(jugadorMain);	
				informar("creacion de usuario exitosa!");
				estadoFlujo = EstadosFlujo.MENU_PRINCIPAL;
				esperandoTecla = true;
			} else { 
				informar("nombre de usuario existente, ingrese otro");
			}
		} else {
			informar("ingreso mal los parametros de entrada!!");
		}
	}
	
	//----------------------------------------------------------------------------
	
	// mwnu principal ------------------------------------------------------------
	
	private void menuPrincipal() {
    	clearScreen();
    	println(OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal());
	}
	
	private void opcionesPrincipal(String entrada) {
		switch(entrada) {
		case "1":
			controlador.jugar();
			estadoFlujo = EstadosFlujo.MOVIMIENTOS;
			break;
		case "2" :
			
			break;
		case "3" :
			break;
		default :
			
			break;
		}
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
    	pantalla.append("$ " + texto + "\n");
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
		menuInicioSesion();
	}
	
	@Override
    public void menuInicioSesion() {
    	clearScreen();
    	println(OpcionesInicioSesion.mostrarOpcionesInicioSesion());
	}

	@Override
	public void mostrarPartida(TableroLectura tablero, JugadorLectura jugador) {
		if(this.estadoFlujo != EstadosFlujo.MOVIMIENTOS) {
			this.estadoFlujo = EstadosFlujo.MOVIMIENTOS;
			clearScreen();
			informar("¡¡Comienza la partida!!");
		} 
		
		pantalla.append(tablero.toString() + "\n");
		informar(jugador, "Le toca al jugador: ");
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
	public void mostrarGanador(JugadorLectura ganador) {
		
	}
	
	@Override
	public void mostrarSalaDeEspera() {
		clearScreen();
		estadoFlujo = EstadosFlujo.ESPERA;
		println(Banner.esperandoJugador);
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
	
	@Override
	public void setControladorJugador(JugadorController controladorJugador) {
		this.controladorJugador = controladorJugador;
	}
	//---------------------------------------------------------------------------


}

