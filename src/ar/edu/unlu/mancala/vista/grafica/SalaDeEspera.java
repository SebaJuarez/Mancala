package ar.edu.unlu.mancala.vista.grafica;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.grafica.listener.Cerrable;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;

public class SalaDeEspera extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cerrable listener;

	
	
	public SalaDeEspera() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTresPuntitos = new JLabel("");
		lblTresPuntitos.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblTresPuntitos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTresPuntitos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTresPuntitos.setForeground(Color.WHITE);
		lblTresPuntitos.setBounds(117, 135, 553, 106);
		contentPane.add(lblTresPuntitos);
		
		JLabel lblFondoSalaEspera = new JLabel("");
		lblFondoSalaEspera.setBounds(0, 0, 790, 470);
		lblFondoSalaEspera.setIcon(new ImageIcon(getClass().getResource("/fondo_sala_de_espera.png")));
		contentPane.add(lblFondoSalaEspera);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(500, new ActionListener() {
            int numPuntos = 0;
            String puntos = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                numPuntos++;
                if (numPuntos > 3) {
                    numPuntos = 0;
                    puntos = "";
                } else {
                    puntos += ".";
                }
                lblTresPuntitos.setText(puntos);
            }
        });
        timer.start();
        
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});
        
    }



	public Cerrable getListener() {
		return listener;
	}



	public void setListener(Cerrable listener) {
		this.listener = listener;
	}
}
