package ar.edu.unlu.mancala.vista.grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reglas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VolverListener listener;
	private String[] photoPaths;
	private JLabel lblFondoRegla;
	private int currentIndex;

	public Reglas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSiguiente = new JButton("->");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteFoto();
			}
		});

		JButton btnAnterior = new JButton("<-");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorFoto();
			}
		});

		JButton btnSalir = new JButton("VOLVER");
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
		btnAnterior.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnterior.setOpaque(false);
		btnAnterior.setForeground(Color.WHITE);
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAnterior.setContentAreaFilled(false);
		btnAnterior.setBorderPainted(false);
		btnAnterior.setBounds(10, 319, 96, 53);
		contentPane.add(btnAnterior);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setOpaque(false);
		btnSiguiente.setForeground(new Color(255, 255, 255));
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSiguiente.setContentAreaFilled(false);
		btnSiguiente.setBorderPainted(false);
		btnSiguiente.setBounds(680, 319, 96, 53);
		contentPane.add(btnSiguiente);

		lblFondoRegla = new JLabel("");
		lblFondoRegla.setBounds(0, 0, 790, 470);
		contentPane.add(lblFondoRegla);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});

		photoPaths = new String[] { "/regla1.png", "/regla2.png", "/regla3.png", "/regla4.png", "/regla5.png" };

		mostrarFoto(0);
	}

	// Método para mostrar la foto en el índice dado
	private void mostrarFoto(int index) {
		if (index >= 0 && index < photoPaths.length) {
			lblFondoRegla.setIcon(new ImageIcon(getClass().getResource(photoPaths[index])));
			currentIndex = index;
		}
	}

	private void siguienteFoto() {
		int nextIndex = (currentIndex + 1) % photoPaths.length;
		mostrarFoto(nextIndex);
	}

	private void anteriorFoto() {
		int prevIndex = (currentIndex - 1 + photoPaths.length) % photoPaths.length;
		mostrarFoto(prevIndex);
	}

	public VolverListener getListener() {
		return listener;
	}

	public void setListener(VolverListener listener) {
		this.listener = listener;
	}
}
