package ar.edu.unlu.mancala.vista.grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;

public class Estadistica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VolverListener listener;
	private JLabel lblGanadasP;
	private JLabel lblPerdidasP;
	private JLabel lblEmpatadasP;
	private JLabel lblGanadasRate;
	private JLabel lblPerdidasRate;
	private JLabel lblEmpatadasRate;
	private JButton btnSalir;

	public Estadistica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSalir = new JButton("VOLVER");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onVolverButtonClick();
			}
		});
		btnSalir.setOpaque(false);
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(614, 10, 162, 33);
		contentPane.add(btnSalir);

		lblEmpatadasRate = new JLabel("0");
		lblEmpatadasRate.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmpatadasRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpatadasRate.setForeground(Color.WHITE);
		lblEmpatadasRate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmpatadasRate.setBounds(532, 323, 139, 47);
		contentPane.add(lblEmpatadasRate);

		lblPerdidasRate = new JLabel("0");
		lblPerdidasRate.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPerdidasRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidasRate.setForeground(Color.WHITE);
		lblPerdidasRate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPerdidasRate.setBounds(326, 323, 139, 47);
		contentPane.add(lblPerdidasRate);

		lblGanadasRate = new JLabel("0");
		lblGanadasRate.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanadasRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadasRate.setForeground(Color.WHITE);
		lblGanadasRate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGanadasRate.setBounds(125, 323, 139, 47);
		contentPane.add(lblGanadasRate);

		lblGanadasP = new JLabel("0");
		lblGanadasP.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanadasP.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadasP.setForeground(Color.WHITE);
		lblGanadasP.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGanadasP.setBounds(125, 179, 139, 47);
		contentPane.add(lblGanadasP);

		lblPerdidasP = new JLabel("0");
		lblPerdidasP.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPerdidasP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidasP.setForeground(Color.WHITE);
		lblPerdidasP.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPerdidasP.setBounds(326, 179, 139, 47);
		contentPane.add(lblPerdidasP);

		lblEmpatadasP = new JLabel("0");
		lblEmpatadasP.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmpatadasP.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpatadasP.setForeground(Color.WHITE);
		lblEmpatadasP.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmpatadasP.setBounds(532, 179, 139, 47);
		contentPane.add(lblEmpatadasP);

		JLabel lblEmpatadas = new JLabel("EMPATADAS");
		lblEmpatadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmpatadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpatadas.setForeground(Color.WHITE);
		lblEmpatadas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmpatadas.setBounds(532, 129, 139, 47);
		contentPane.add(lblEmpatadas);

		JLabel lblPerdidas = new JLabel("PERDIDAS");
		lblPerdidas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidas.setForeground(Color.WHITE);
		lblPerdidas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPerdidas.setBounds(326, 129, 139, 47);
		contentPane.add(lblPerdidas);

		JLabel lblGanadas = new JLabel("GANADAS");
		lblGanadas.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGanadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadas.setForeground(Color.WHITE);
		lblGanadas.setBounds(125, 129, 139, 47);
		contentPane.add(lblGanadas);

		JLabel lblFondoEstadisticas = new JLabel("");
		lblFondoEstadisticas.setBounds(0, 0, 790, 470);
		contentPane.add(lblFondoEstadisticas);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}

		});

		lblFondoEstadisticas.setIcon(new ImageIcon(getClass().getResource("/fondo_estadisticas.png")));

	}

	public void mostrarEstadisticas(JugadorLectura jugador) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		lblEmpatadasP.setText(jugador.getEmpatadas() + "");
		lblGanadasP.setText(jugador.getGanadas() + "");
		lblPerdidasP.setText(jugador.getPerdidas() + "");
		lblEmpatadasRate.setText(decimalFormat.format(jugador.drawnRate()) + " %");
		lblGanadasRate.setText(decimalFormat.format(jugador.winRate()) + " %");
		lblPerdidasRate.setText(decimalFormat.format(jugador.loseRate()) + " %");
	}

	public VolverListener getListener() {
		return listener;
	}

	public void setListener(VolverListener listener) {
		this.listener = listener;
	}

}
