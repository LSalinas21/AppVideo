package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Registro extends JFrame {

	private JPanel contentPane;
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
	
	private void inicializar() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		
		creaIconoRegistro();
		creaBotones();
		creaCampoDatos();
		
		
		
	}
	private void creaIconoRegistro() {
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		LabelRegistroIcono = new JLabel("Registro");
		LabelRegistroIcono.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(LabelRegistroIcono);
	}
	
	private void creaBotones() {
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
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
		contentPane.add(panelCentro, BorderLayout.CENTER);
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
}
