package ar.edu.unlu.mancala.vista.grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TopJugadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VolverListener listener;
	private List<JLabel> labels;
	private JLabel lblJ1;

	public TopJugadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("VOLVER");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onVolverButtonClick();
			}
		});
		
		JLabel lblJ10 = new JLabel("10");
		lblJ10.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ10.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ10.setForeground(Color.WHITE);
		lblJ10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ10.setBounds(204, 350, 469, 19);
		contentPane.add(lblJ10);
		
		JLabel lblJ9 = new JLabel("9");
		lblJ9.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ9.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ9.setForeground(Color.WHITE);
		lblJ9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ9.setBounds(204, 328, 469, 19);
		contentPane.add(lblJ9);
		
		JLabel lblJ3 = new JLabel("3");
		lblJ3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ3.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ3.setForeground(Color.WHITE);
		lblJ3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ3.setBounds(204, 204, 469, 19);
		contentPane.add(lblJ3);
		
		JLabel lblJ6 = new JLabel("6");
		lblJ6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ6.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ6.setForeground(Color.WHITE);
		lblJ6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ6.setBounds(204, 266, 469, 19);
		contentPane.add(lblJ6);
		
		JLabel lblJ5 = new JLabel("5");
		lblJ5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ5.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ5.setForeground(Color.WHITE);
		lblJ5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ5.setBounds(204, 245, 469, 19);
		contentPane.add(lblJ5);
		
		JLabel lblJ4 = new JLabel("4");
		lblJ4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ4.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ4.setForeground(Color.WHITE);
		lblJ4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ4.setBounds(204, 224, 469, 19);
		contentPane.add(lblJ4);
		
		JLabel lblJ7 = new JLabel("7");
		lblJ7.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ7.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ7.setForeground(Color.WHITE);
		lblJ7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ7.setBounds(204, 287, 469, 19);
		contentPane.add(lblJ7);
		
		JLabel lblJ8 = new JLabel("8");
		lblJ8.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ8.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ8.setForeground(Color.WHITE);
		lblJ8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ8.setBounds(204, 308, 469, 19);
		contentPane.add(lblJ8);
		btnSalir.setOpaque(false);
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(614, 10, 162, 33);
		contentPane.add(btnSalir);
		
		JLabel lblJ2 = new JLabel("2");
		lblJ2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ2.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ2.setForeground(Color.WHITE);
		lblJ2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ2.setBounds(204, 186, 469, 19);
		contentPane.add(lblJ2);
		
		lblJ1 = new JLabel("");
		lblJ1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ1.setHorizontalAlignment(SwingConstants.LEFT);
		lblJ1.setForeground(Color.WHITE);
		lblJ1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJ1.setBounds(243, 129, 500, 47);
		contentPane.add(lblJ1);
		
		labels = List.of(lblJ1, lblJ2, lblJ3, lblJ4, lblJ5, lblJ6, lblJ7, lblJ8, lblJ9, lblJ10);
		
		JLabel lblFondoTop10 = new JLabel("");
		lblFondoTop10.setBounds(0, 0, 790, 470);
		contentPane.add(lblFondoTop10);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		
		});
		
		lblFondoTop10.setIcon(new ImageIcon(getClass().getResource("/fondo_top_10.png")));

	}

	public VolverListener getListener() {
		return listener;
	}

	public void setListener(VolverListener listener) {
		this.listener = listener;
	}

	public void mostrarJugadores(List<JugadorLectura> topTen) {
		labels.forEach(l ->{
			l.setText("");
		});
		lblJ1.setText( " " + topTen.get(0).getNombre() + "    Ganadas : " + topTen.get(0).getGanadas() 
				+ "    Perdidas : " + topTen.get(0).getPerdidas() + "    Empatadas : " +  topTen.get(0).getEmpatadas() );
		
		for(int i = 1; i < topTen.size(); i++) {
			labels.get(i).setText(i + ") " + topTen.get(i).getNombre() + "    Ganadas : " + topTen.get(i).getGanadas() 
			+ "    Perdidas : " + topTen.get(i).getPerdidas() + "    Empatadas : " +  topTen.get(i).getEmpatadas() );
		}
	}

}

