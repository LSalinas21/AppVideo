package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Registro {

	private JFrame frmRegistro;
	private JTextField textNombre;
	private JTextField textApellido;
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
	private JDateChooser dateChooser;
	private JLabel lblPasswordError;
	private JLabel lblUsuarioError;
	private JLabel lblEmailError;
	private JLabel lblFechaError;
	private JLabel lblApellidosError;
	private JLabel lblNombreError;
	private int frameLargo = 340;
	private Border bordeDefault;
	
	private Pattern pat = Pattern.compile("[a-zA-Z]*@[a-zA-Z]*\\.[a-zA-Z]*");
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
		
		frmRegistro.setBounds(100, 100, 450, 340);
		
		
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
				textEmail.setText(null);
				textUsuario.setText(null);
				textPass.setText(null);
				dateChooser.setCalendar(null);
				
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
							new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
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
		gbl_panelCentro.columnWidths = new int[]{50, 0, 158, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{40, 0, 0, 0, 0, 34, 0, 25, 0, 0, 0, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		textNombre.setColumns(12);
		bordeDefault = textNombre.getBorder();
		panelCentro.add(textNombre,gbc_textNombre);
	}
	
	private void creaLabelApellido() {

		lblNombreError = new JLabel("");
		lblNombreError.setEnabled(false);
		GridBagConstraints gbc_lblNombreError = new GridBagConstraints();
		gbc_lblNombreError.gridwidth = 2;
		gbc_lblNombreError.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombreError.gridx = 2;
		gbc_lblNombreError.gridy = 2;
		panelCentro.add(lblNombreError, gbc_lblNombreError);
		
		
		labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.anchor = GridBagConstraints.EAST;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 1;
		gbc_labelApellido.gridy = 3;
		
		panelCentro.add(labelApellido,gbc_labelApellido);
		
	}
	
	private void creaTextApellido() {
		
		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 3;
		textApellido.setColumns(10);
		
		panelCentro.add(textApellido,gbc_textApellido);
	}
	
	private void creaLabelFechaN() {

		lblApellidosError = new JLabel("");
		lblApellidosError.setEnabled(false);
		GridBagConstraints gbc_lblApellidosError = new GridBagConstraints();
		gbc_lblApellidosError.gridwidth = 2;
		gbc_lblApellidosError.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidosError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblApellidosError.gridx = 2;
		gbc_lblApellidosError.gridy = 4;
		panelCentro.add(lblApellidosError, gbc_lblApellidosError);
		
		labelFechaN = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaN = new GridBagConstraints();
		gbc_labelFechaN.fill = GridBagConstraints.VERTICAL;
		gbc_labelFechaN.anchor = GridBagConstraints.EAST;
		gbc_labelFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaN.gridx = 1;
		gbc_labelFechaN.gridy = 5;
		
		panelCentro.add(labelFechaN,gbc_labelFechaN);
	}
	
	private void creaTextFechaN() {
	}
	
	private void creaLabelEmail() {
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 5;
		panelCentro.add(dateChooser, gbc_dateChooser);
		
		lblFechaError = new JLabel("");
		lblFechaError.setEnabled(false);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 6;
		panelCentro.add(lblFechaError, gbc_lblNewLabel);
		
		
		labelEmail = new JLabel("e-mail");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.EAST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 7;
		
		panelCentro.add(labelEmail,gbc_labelEmail);
	}
	
	private void creaTextEmail() {
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 7;
		textEmail.setColumns(10);
		
		panelCentro.add(textEmail,gbc_textEmail);
	}
	
	private void creaLabelUsuario() {
		
		lblEmailError = new JLabel("");
		lblEmailError.setEnabled(false);
		GridBagConstraints gbc_lblEmailError = new GridBagConstraints();
		gbc_lblEmailError.gridwidth = 2;
		gbc_lblEmailError.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmailError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmailError.gridx = 2;
		gbc_lblEmailError.gridy = 8;
		panelCentro.add(lblEmailError, gbc_lblEmailError);
		
		labelUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 9;
		
		panelCentro.add(labelUsuario,gbc_labelUsuario);
	}
	
	private void creaTextUsuario() {
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 9;
		textUsuario.setColumns(10);
		
		panelCentro.add(textUsuario,gbc_textUsuario);
	}
	
	private void creaLabelPass() {
	
		lblUsuarioError = new JLabel("");
		lblUsuarioError.setEnabled(false);
		GridBagConstraints gbc_lblUsuarioError = new GridBagConstraints();
		gbc_lblUsuarioError.gridwidth = 2;
		gbc_lblUsuarioError.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsuarioError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsuarioError.gridx = 2;
		gbc_lblUsuarioError.gridy = 10;
		panelCentro.add(lblUsuarioError, gbc_lblUsuarioError);
		
		
		labelPass = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_labelPass = new GridBagConstraints();
		gbc_labelPass.anchor = GridBagConstraints.EAST;
		gbc_labelPass.insets = new Insets(0, 0, 5, 5);
		gbc_labelPass.gridx = 1;
		gbc_labelPass.gridy = 11;
		
		panelCentro.add(labelPass,gbc_labelPass);
	}
	
	private void creaTextPass(){
		
		textPass = new JPasswordField();
		GridBagConstraints gbc_textPass = new GridBagConstraints();
		gbc_textPass.insets = new Insets(0, 0, 5, 5);
		gbc_textPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPass.gridx = 2;
		gbc_textPass.gridy = 11;
		
		
		panelCentro.add(textPass,gbc_textPass);
		{
			lblPasswordError = new JLabel("");
			lblPasswordError.setEnabled(false);
			GridBagConstraints gbc_lblPasswordError = new GridBagConstraints();
			gbc_lblPasswordError.gridwidth = 2;
			gbc_lblPasswordError.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblPasswordError.gridx = 2;
			gbc_lblPasswordError.gridy = 12;
			panelCentro.add(lblPasswordError, gbc_lblPasswordError);
		}
		
	}
	private boolean checkFields() {
		boolean salida = true;
		/* borrar todos los errores en pantalla */
		//ocultarErrores();
		int tamFrameAux = frameLargo;
		
		if (textNombre.getText().trim().isEmpty()) {
			
			lblNombreError.setText("El campo Nombre es obligatorio");
			lblNombreError.setVisible(true);
			labelNombre.setForeground(Color.RED);
			textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			
			tamFrameAux +=10;
		}else {
			
			lblNombreError.setVisible(false);
			labelNombre.setForeground(null);
			textNombre.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		if (textApellido.getText().trim().isEmpty()) {
			lblApellidosError.setText("El campo apellido no debe estar vacio");
			lblApellidosError.setVisible(true);
			labelApellido.setForeground(Color.RED);
			textApellido.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			tamFrameAux +=10;

		}else {
			
			lblApellidosError.setVisible(false);
			labelApellido.setForeground(null);
			textApellido.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		if (textEmail.getText().trim().isEmpty()) {
			lblEmailError.setText("El correo es obligatorio");
			lblEmailError.setVisible(true);
			labelEmail.setForeground(Color.RED);
			textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			tamFrameAux +=10;

		}else if(!textEmail.getText().trim().isEmpty()){
			
			Matcher mat = pat.matcher(textEmail.getText().trim());
			
			if (mat.matches()) {
				
				lblEmailError.setVisible(false);
				labelEmail.setForeground(null);
				textEmail.setBorder(bordeDefault);
				
				tamFrameAux -=10;
		     } else {
		    	 
		    	 lblEmailError.setText("Formato incorrecto Ejm: correo@um.es");
		    	 lblEmailError.setVisible(true);
		    	 labelEmail.setForeground(Color.RED);
		    	 textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
		    	 salida = false;
		    	 tamFrameAux +=10;                                                                               
		     }
			
		}else {
			
			lblEmailError.setVisible(false);
			labelEmail.setForeground(null);
			textEmail.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		if (textUsuario.getText().trim().isEmpty()) {
			lblUsuarioError.setText("El usuario es obligatorio");
			lblUsuarioError.setVisible(true);
			labelUsuario.setForeground(Color.RED);
			textUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			tamFrameAux +=10;

		}else {
			
			lblUsuarioError.setVisible(false);
			labelUsuario.setForeground(null);
			textUsuario.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		String password = new String(textPass.getPassword());
		if (password.isEmpty()) {
			lblPasswordError.setText("El password no puede estar vacio");
			lblPasswordError.setVisible(true);
			labelPass.setForeground(Color.RED);
			textPass.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			tamFrameAux +=10;

		}else {
			
			lblPasswordError.setVisible(false);
			labelPass.setForeground(null);
			textPass.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		
		if(dateChooser.getDate() == null) {
			
			lblFechaError.setText("Debes indicar tu fecha de nacimiento");
			lblFechaError.setVisible(true);
			labelFechaN.setForeground(Color.RED);
			dateChooser.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			tamFrameAux +=10;

		}else {
			
			lblFechaError.setVisible(false);
			labelFechaN.setForeground(null);
			dateChooser.setBorder(bordeDefault);
			
			tamFrameAux -=10;
			
		}
		
		if (!lblUsuarioError.getText().isEmpty() && Controlador.getUnicaInstancia().esUsuarioRegistrado(textUsuario.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			labelUsuario.setForeground(Color.RED);
			textUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
			
			
		}
		frmRegistro.setSize(450, tamFrameAux);
		frmRegistro.revalidate();
		
		
		return salida;
	}
}
