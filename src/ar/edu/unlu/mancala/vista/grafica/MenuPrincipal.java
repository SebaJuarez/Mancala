package ar.edu.unlu.mancala.vista.grafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.grafica.listener.Cerrable;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuPrincipalListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame implements Cerrable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuPrincipalListener listener;
	private List<JButton> botones;
	private Reglas reglas = new Reglas();
	private JLabel lblInforme;
	
	
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInforme = new JLabel("");
		lblInforme.setHorizontalAlignment(SwingConstants.LEFT);
		lblInforme.setForeground(Color.ORANGE);
		lblInforme.setBounds(147, 372, 326, 27);
		contentPane.add(lblInforme);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onSalirButtonClick();
			}
		});
		btnSalir.setOpaque(false);
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(229, 338, 348, 33);
		contentPane.add(btnSalir);
		
		JButton btnReglasDelJuego = new JButton("REGLAMENTO");
		btnReglasDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onReglamentoButtonClick();
			}
		});
		btnReglasDelJuego.setOpaque(false);
		btnReglasDelJuego.setForeground(Color.WHITE);
		btnReglasDelJuego.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReglasDelJuego.setContentAreaFilled(false);
		btnReglasDelJuego.setBorderPainted(false);
		btnReglasDelJuego.setBounds(229, 293, 348, 33);
		contentPane.add(btnReglasDelJuego);
		
		JButton btnMisEstadisticas = new JButton("MIS ESTADISTICAS");
		btnMisEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onMisEstadisticasClick();
			}
		});
		btnMisEstadisticas.setOpaque(false);
		btnMisEstadisticas.setForeground(Color.WHITE);
		btnMisEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMisEstadisticas.setContentAreaFilled(false);
		btnMisEstadisticas.setBorderPainted(false);
		btnMisEstadisticas.setBounds(229, 250, 348, 33);
		contentPane.add(btnMisEstadisticas);
		
		JButton btnTopGanadores = new JButton("TOP GANADORES");
		btnTopGanadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onTopGanadoresButtonClick();
			}
		});
		btnTopGanadores.setOpaque(false);
		btnTopGanadores.setForeground(Color.WHITE);
		btnTopGanadores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTopGanadores.setContentAreaFilled(false);
		btnTopGanadores.setBorderPainted(false);
		btnTopGanadores.setBounds(229, 207, 348, 33);
		contentPane.add(btnTopGanadores);
		
		JButton btnBuscarPartida = new JButton("BUSCAR PARTIDA");
		btnBuscarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onBuscarPartidaButtonClick();
			}
		});
		btnBuscarPartida.setOpaque(false);
		btnBuscarPartida.setForeground(Color.WHITE);
		btnBuscarPartida.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBuscarPartida.setContentAreaFilled(false);
		btnBuscarPartida.setBorderPainted(false);
		btnBuscarPartida.setBounds(229, 164, 348, 33);
		contentPane.add(btnBuscarPartida);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/fondo_menu_inicio_de_sesion.png")));
		lblNewLabel.setBounds(0, 0, 790, 470);
		contentPane.add(lblNewLabel);
		
		botones = List.of(btnBuscarPartida, btnTopGanadores, btnMisEstadisticas, btnReglasDelJuego, btnSalir);
		
		botones.forEach(boton ->{
			boton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					// Cambia el tamaño del botón al pasar el mouse por encima
					boton.setSize(boton.getWidth() + 5, boton.getHeight() + 5);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// Restaura el tamaño original del botón al salir el mouse
					boton.setSize(boton.getWidth() - 5, boton.getHeight() - 5);
				}
			});
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		
		});
		
	}


	public void mostrarReglas() {
		botones.forEach(b -> b.setEnabled(false));
		reglas.setListener(this);
		reglas.setVisible(true);
	}


	public MenuPrincipalListener getListener() {
		return listener;
	}


	public void setListener(MenuPrincipalListener listener) {
		this.listener = listener;
	}


	@Override
	public void onCloseWindow() {
		botones.forEach(b -> b.setEnabled(true));
	}


	public void informar(String string) {
		lblInforme.setText(string);
	}
	
	public void mostrarGanador() {
		
	}
}
