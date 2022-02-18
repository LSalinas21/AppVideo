package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Registro {

	private JFrame frmRegistro;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textFecha;
	private JTextField textEmail;
	private JTextField textUsuario;
	private JPasswordField textPass;
	private JPanel panelNorte;
	private JLabel LabelRegistroIcono;
	private JPanel panelSur;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JButton botonLimpiar;
	private JPanel panelCentro;
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelFechaN;
	private JLabel labelEmail;
	private JLabel labelUsuario;
	private JLabel labelPass;
	/**
	 * Create the frame.
	 */
	public Registro() {
		
		inicializar();
		
	}
	public void mostrarVentana() {
		
		frmRegistro.setLocationRelativeTo(null);
		frmRegistro.setVisible(true);
	}
	
	private void inicializar() {
		
		frmRegistro = new JFrame();
		frmRegistro.setTitle("Registro AppVideo");
		frmRegistro.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmRegistro.getContentPane().setLayout(new BorderLayout(0,0));
		frmRegistro.setResizable(false);
		frmRegistro.getContentPane().setLayout(new BorderLayout());
		
		frmRegistro.setBounds(100, 100, 450, 300);

		
		creaIconoRegistro();
		creaBotones();
		creaCampoDatos();
		creaManejadorBotonAceptar();
		creaManejadorBotonCancelar();
		creaManejadorBotonLimpiar();
		
		
	}
	private void creaManejadorBotonCancelar() {
		
		botonCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmRegistro.dispose();
				
			}
			
		});
		
	}
	private void creaManejadorBotonLimpiar() {
		
		botonLimpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textNombre.setText(null);
				textApellido.setText(null);
				textFecha.setText(null);
				textEmail.setText(null);
				textUsuario.setText(null);
				textPass.setText(null);
				
			}
			
		});
		
	}
	private void creaManejadorBotonAceptar() {
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK = false;
				OK = checkFields();
				if (OK) {
					boolean registrado = false;
					registrado = Controlador.getUnicaInstancia().registrarUsuario(textNombre.getText(),
							textApellido.getText(), textEmail.getText(), textUsuario.getText(),
							new String(textPass.getPassword()), 
							textFecha.getText());
					if (registrado) {
						JOptionPane.showMessageDialog(frmRegistro, "Usuario registrado correctamente.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						
						Login loginView = new Login();
						loginView.mostrarVentana();
						frmRegistro.dispose();
					} else {
						JOptionPane.showMessageDialog(frmRegistro, "No se ha podido llevar a cabo el registro.\n",
								"Registro", JOptionPane.ERROR_MESSAGE);
						frmRegistro.setTitle("Login Gestor Eventos");
					}
				}
			}
		});
	}
	private void creaIconoRegistro() {
		
		panelNorte = new JPanel();
		frmRegistro.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		LabelRegistroIcono = new JLabel("Registro");
		LabelRegistroIcono.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(LabelRegistroIcono);
	}
	
	private void creaBotones() {
		
		panelSur = new JPanel();
		frmRegistro.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		botonAceptar = new JButton("Aceptar");
		panelSur.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		panelSur.add(botonCancelar);
		
		botonLimpiar = new JButton("Limpiar");
		panelSur.add(botonLimpiar);
		
	}
	
	private void creaCampoDatos() {
		
		panelCentro = new JPanel();
		frmRegistro.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{50, 0, 20, 0};
		gbl_panelCentro.rowHeights = new int[]{40, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		creaLabelNombre();
		creaTextNombre();
		creaLabelApellido();
		creaTextApellido();
		creaLabelFechaN();
		creaTextFechaN();
		creaLabelEmail();
		creaTextEmail();
		creaLabelUsuario();
		creaTextUsuario();
		creaLabelPass();
		creaTextPass();
	}
	
	private void creaLabelNombre() {
		
		labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.anchor = GridBagConstraints.EAST;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 1;
		
		panelCentro.add(labelNombre,gbc_labelNombre);
	}
	private void creaTextNombre() {
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		textNombre.setColumns(12);
		
		panelCentro.add(textNombre,gbc_textNombre);
	}
	
	private void creaLabelApellido() {
		
		labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.anchor = GridBagConstraints.EAST;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 1;
		gbc_labelApellido.gridy = 2;
		
		panelCentro.add(labelApellido,gbc_labelApellido);
		
	}
	
	private void creaTextApellido() {
		
		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 2;
		textApellido.setColumns(10);
		
		panelCentro.add(textApellido,gbc_textApellido);
	}
	
	private void creaLabelFechaN() {
		
		labelFechaN = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaN = new GridBagConstraints();
		gbc_labelFechaN.anchor = GridBagConstraints.EAST;
		gbc_labelFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaN.gridx = 1;
		gbc_labelFechaN.gridy = 3;
		
		panelCentro.add(labelFechaN,gbc_labelFechaN);
	}
	
	private void creaTextFechaN() {
		
		textFecha = new JTextField();
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.insets = new Insets(0, 0, 5, 0);
		gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFecha.gridx = 2;
		gbc_textFecha.gridy = 3;
		textFecha.setColumns(10);
		
		panelCentro.add(textFecha,gbc_textFecha);
	}
	
	private void creaLabelEmail() {
		
		labelEmail = new JLabel("e-mail");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.EAST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		
		panelCentro.add(labelEmail,gbc_labelEmail);
	}
	
	private void creaTextEmail() {
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 4;
		textEmail.setColumns(10);
		
		panelCentro.add(textEmail,gbc_textEmail);
	}
	
	private void creaLabelUsuario() {
		
		labelUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 5;
		
		panelCentro.add(labelUsuario,gbc_labelUsuario);
	}
	
	private void creaTextUsuario() {
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 5;
		textUsuario.setColumns(10);
		
		panelCentro.add(textUsuario,gbc_textUsuario);
	}
	
	private void creaLabelPass() {
		
		
		labelPass = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_labelPass = new GridBagConstraints();
		gbc_labelPass.anchor = GridBagConstraints.EAST;
		gbc_labelPass.insets = new Insets(0, 0, 0, 5);
		gbc_labelPass.gridx = 1;
		gbc_labelPass.gridy = 6;
		
		panelCentro.add(labelPass,gbc_labelPass);
	}
	
	private void creaTextPass(){
		
		textPass = new JPasswordField();
		GridBagConstraints gbc_textPass = new GridBagConstraints();
		gbc_textPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPass.gridx = 2;
		gbc_textPass.gridy = 6;
		
		
		panelCentro.add(textPass,gbc_textPass);
	}
	private boolean checkFields() {
		boolean salida = true;
		/* borrar todos los errores en pantalla */
		//ocultarErrores();
		if (textNombre.getText().trim().isEmpty()) {
			//lblNombreError.setVisible(true);
			labelNombre.setForeground(Color.RED);
			textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textApellido.getText().trim().isEmpty()) {
			//lblApellidosError.setVisible(true);
			labelApellido.setForeground(Color.RED);
			textApellido.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textEmail.getText().trim().isEmpty()) {
			//lblEmailError.setVisible(true);
			labelEmail.setForeground(Color.RED);
			textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textUsuario.getText().trim().isEmpty()) {
			//lblUsuarioError.setText("El usuario es obligatorio");
			//labelUsuarioError.setVisible(true);
			labelUsuario.setForeground(Color.RED);
			textUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(textPass.getPassword());
		//String password2 = new String(textPasswordChk.getPassword());
		if (password.isEmpty()) {
			//lblPasswordError.setText("El password no puede estar vacio");
			//lblPasswordError.setVisible(true);
			labelPass.setForeground(Color.RED);
			textPass.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		/*if (password2.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			lblPasswordChk.setForeground(Color.RED);
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (!password.equals(password2)) {
			lblPasswordError.setText("Los dos passwords no coinciden");
			lblPasswordError.setVisible(true);
			lblPassword.setForeground(Color.RED);
			lblPasswordChk.setForeground(Color.RED);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			txtPasswordChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		Comprobar que no exista otro usuario con igual login 
		if (!lblUsuarioError.getText().isEmpty() && Controlador.getUnicaInstancia().esUsuarioRegistrado(txtUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}*/
		if (textFecha.getText().isEmpty()) {
			//lblFechaNacimientoError.setVisible(true);
			labelFechaN.setForeground(Color.RED);
			textFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}

		frmRegistro.revalidate();
		
		
		return salida;
	}
}
