package ar.edu.unlu.mancala.vista.grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.grafica.listener.FormularioCreacionUsuarioListener;
import ar.edu.unlu.mancala.vista.grafica.listener.FormularioInicioSesionListener;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuInicioSesionListener;

public class MenuInicioSesion extends JFrame implements FormularioCreacionUsuarioListener , FormularioInicioSesionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panelInicioFondo;
	private JButton btnInicioSesion;
	private JButton btnCrearCuenta;
	private MenuInicioSesionListener listener;
	private FormularioInicioSesion formularioInicioSesion = new FormularioInicioSesion();
	private FormularioCreacionUsuario formularioCreacionUsuario = new FormularioCreacionUsuario();

	public MenuInicioSesion() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		
		});

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		JPanel panelInicioSesion = new JPanel();
		panelInicioSesion.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelInicioSesion);
		panelInicioSesion.setLayout(null);

		panelInicioFondo = new JPanel();
		panelInicioFondo.setBounds(0, 0, 790, 470);
		panelInicioSesion.add(panelInicioFondo);
		panelInicioFondo.setLayout(null);

		JPanel panelOpcionesInicioSesion = new JPanel();
		panelOpcionesInicioSesion.setBackground(SystemColor.inactiveCaptionBorder);
		panelOpcionesInicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelOpcionesInicioSesion.setBounds(193, 174, 387, 165);
		panelOpcionesInicioSesion.setOpaque(false);
		panelInicioFondo.add(panelOpcionesInicioSesion);
		panelOpcionesInicioSesion.setLayout(null);
		// boton crear cuenta

		btnCrearCuenta = new JButton("CREAR CUENTA");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formularioCreacionUsuario.setFormularioInicioSesionListener(MenuInicioSesion.this);
				btnCrearCuenta.setEnabled(false);
				btnInicioSesion.setEnabled(false);
				formularioCreacionUsuario.setLocationRelativeTo(null);
				formularioCreacionUsuario.setVisible(true);
			}
		});
		btnCrearCuenta.setOpaque(false);
		btnCrearCuenta.setContentAreaFilled(false);
		btnCrearCuenta.setBorderPainted(false);
		btnCrearCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCrearCuenta.setSize(btnCrearCuenta.getWidth() + 10, btnCrearCuenta.getHeight() + 10);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCrearCuenta.setSize(btnCrearCuenta.getWidth() - 10, btnCrearCuenta.getHeight() - 10);
			}
		});
		btnCrearCuenta.setForeground(Color.WHITE);
		btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnCrearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrearCuenta.setContentAreaFilled(false);
		btnCrearCuenta.setBorderPainted(false);
		btnCrearCuenta.setBounds(50, 98, 287, 57);
		panelOpcionesInicioSesion.add(btnCrearCuenta);

		// boton inicio sesion
		btnInicioSesion = new JButton("INICIAR SESION");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formularioInicioSesion.setFormularioInicioSesionListener(MenuInicioSesion.this);
				btnCrearCuenta.setEnabled(false);
				btnInicioSesion.setEnabled(false);
				formularioInicioSesion.setLocationRelativeTo(null);
				formularioInicioSesion.setVisible(true);
			}
		});
		btnInicioSesion.setOpaque(false);
		btnInicioSesion.setContentAreaFilled(false);
		btnInicioSesion.setBorderPainted(false);
		btnInicioSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Cambia el tamaño del botón al pasar el mouse por encima
				btnInicioSesion.setSize(btnInicioSesion.getWidth() + 10, btnInicioSesion.getHeight() + 10);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura el tamaño original del botón al salir el mouse
				btnInicioSesion.setSize(btnInicioSesion.getWidth() - 10, btnInicioSesion.getHeight() - 10);
			}
		});
		btnInicioSesion.setForeground(SystemColor.text);
		btnInicioSesion.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnInicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInicioSesion.setBounds(50, 24, 287, 57);
		panelOpcionesInicioSesion.add(btnInicioSesion);

		JLabel lblFondoPantalla = new JLabel("");
		lblFondoPantalla.setBounds(0, 0, 790, 470);
		lblFondoPantalla.setIcon(new ImageIcon(getClass().getResource("/fondo_menu_inicio_de_sesion.png")));
		panelInicioFondo.add(lblFondoPantalla);
		
	}
	
	public MenuInicioSesionListener getListener() {
		return listener;
	}

	public void setListener(MenuInicioSesionListener listener) {
		this.listener = listener;
	}

	public Object getFormularioInicioSesion() {
		return this.formularioInicioSesion;
	}

	public Object getFormularioCreacionUsuario() {
		return this.formularioCreacionUsuario;
	}
	
	@Override
	public void onEntrarButtonClick(String usuario, String contrasenia)  {
		listener.onEntrarButtonClick(usuario, contrasenia);
	}

	@Override
	public void onCrearButtonClick(String usuario, String contrasenia) {
		listener.onCrearButtonClick(usuario, contrasenia);
	}

	@Override
	public void onCloseWindow() {
		btnCrearCuenta.setEnabled(true);
		btnInicioSesion.setEnabled(true);
	}

	public void terminar() {
		this.setVisible(false);
		this.formularioCreacionUsuario.setVisible(false);
		this.formularioInicioSesion.setVisible(false);
	}

}
