package ar.edu.unlu.mancala.vista.grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ar.edu.unlu.mancala.vista.grafica.listener.FormularioCreacionUsuarioListener;

public class FormularioCreacionUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private FormularioCreacionUsuarioListener listener;
	private JLabel aviso;

	public FormularioCreacionUsuario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});
		setResizable(false);
		setVisible(false);
		setBounds(193, 174, 482, 263);
		getContentPane().setLayout(null);
		setBackground(SystemColor.inactiveCaptionBorder);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBounds(0, 0, 468, 226);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);

		aviso = new JLabel("");
		aviso.setHorizontalAlignment(SwingConstants.CENTER);
		aviso.setForeground(SystemColor.text);
		aviso.setBounds(10, 181, 448, 13);
		panelFormulario.add(aviso);

		JTextField textFieldUsuario = new JTextField();
		textFieldUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldUsuario.getText().length() >= 20) {
					e.consume();
				}
			}
		});

		textFieldUsuario.setBounds(50, 100, 167, 19);
		panelFormulario.add(textFieldUsuario);
		textFieldUsuario.setCaretColor(new Color(255, 255, 255));
		textFieldUsuario.setOpaque(false);
		textFieldUsuario.setForeground(Color.WHITE);
		textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsuario.setColumns(10);

		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(102, 77, 86, 13);
		panelFormulario.add(lblUsuario);
		lblUsuario.setForeground(SystemColor.text);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnCrear = new JButton("CREAR");
		btnCrear.setBounds(136, 129, 208, 49);
		panelFormulario.add(btnCrear);
		btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrear.setOpaque(false);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnCrear.setContentAreaFilled(false);
		btnCrear.setBorderPainted(false);

		JLabel lblContrasenia = new JLabel("CONTRASEÑA");
		lblContrasenia.setBounds(286, 77, 105, 13);
		panelFormulario.add(lblContrasenia);
		lblContrasenia.setForeground(Color.WHITE);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JTextField textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(250, 100, 167, 19);
		panelFormulario.add(textFieldContrasenia);
		textFieldContrasenia.setOpaque(false);
		textFieldContrasenia.setColumns(10);
		textFieldContrasenia.setHorizontalAlignment(SwingConstants.CENTER); // Alinea el texto al centro
		textFieldContrasenia.setCaretColor(Color.WHITE);
		textFieldContrasenia.setForeground(Color.WHITE);

		textFieldContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldContrasenia.getText().length() >= 20) {
					e.consume();
				}
			}
		});

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aviso.setText("");
				String usuario = textFieldUsuario.getText();
				String contrasenia = textFieldContrasenia.getText();
				// Usuario no puede estar vacío y debe tener menos de 8 caracteres
				if (usuario.isEmpty() || usuario.length() > 8 || usuario.length() < 4) {
					aviso.setText("El usuario debe tener entre 4 y 8 caracteres.");
					return;
				}
				// Contraseña no puede estar vacía y debe tener menos de 15 caracteres
				if (contrasenia.isEmpty() || contrasenia.length() > 15 || contrasenia.length() < 5) {
					aviso.setText("La contraseña debe tener entre 5 y 15 caracteres.");
					return;
				}
				// Usuario y contraseña no pueden contener espacios
				if (usuario.contains(" ") || contrasenia.contains(" ")) {
					aviso.setText("El usuario y la contraseña no pueden contener espacios.");
					return;
				}
				listener.onCrearButtonClick(usuario, contrasenia);

			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/fondo_creacion_login.png")));
		lblNewLabel.setBounds(0, 0, 468, 226);
		panelFormulario.add(lblNewLabel);

		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Cambia el tamaño del botón al pasar el mouse por encima
				btnCrear.setSize(btnCrear.getWidth() + 10, btnCrear.getHeight() + 10);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura el tamaño original del botón al salir el mouse
				btnCrear.setSize(btnCrear.getWidth() - 10, btnCrear.getHeight() - 10);
			}
		});

		// cuando hago focus en el nombre o constraseña, el aviso se limpia
		textFieldUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				aviso.setText("");
			}
		});
		textFieldContrasenia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				aviso.setText("");
			}
		});

	}

	public void setFormularioInicioSesionListener(FormularioCreacionUsuarioListener listener) {
		this.listener = listener;
	}

	public void mostrarAviso(String string) {
		aviso.setText(string);
	}
}