package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login{

	private JFrame frmLogin;
	private JTextField textUser;
	private JPasswordField fieldPass;
	private JPanel contentPane,panelNorte,panelCentro,panelSur;
	private JLabel labelLogo,labelUsuario,labelPass;
	private JButton botonLogin,botonRegistro;

	public Login() {
		initialize();
	}
	
	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	private void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setTitle("Login AppVideo");
		frmLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout(0,0));
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 450, 300);
		
		
		configPanelNorte();
		configPanelSur();
		configPanelCentro();
		creaManejadorBotonLogin();
		//creaManejadorBotonRegistro();
		
		
	}
	
	private void creaManejadorBotonLogin() {
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login = Controlador.getUnicaInstancia().loginUsuario(textUser.getText(),
						new String(fieldPass.getPassword()));

				if (login) {
					
					frmLogin.dispose();
				} else
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private void configPanelNorte() {
		
		panelNorte = new JPanel();
		frmLogin.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelLogo = new JLabel("");
		labelLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogo.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/imagenes/Amazon-Prime-Video-Logo.png")));
		panelNorte.add(labelLogo);
		
	}
	private void configPanelSur() {
		
		panelSur = new JPanel();
		frmLogin.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		botonLogin = new JButton("Aceptar");
		panelSur.add(botonLogin);
		
		botonRegistro = new JButton("Registro");
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registro ventanaRegistro= new Registro();
				ventanaRegistro.mostrarVentana();
			}
		});
		panelSur.add(botonRegistro);
		
	}
	private void configPanelCentro() {
		
		panelCentro = new JPanel();
		frmLogin.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{100, 0, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{50, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		labelUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 1;
		panelCentro.add(labelUsuario, gbc_labelUsuario);
		
		textUser = new JTextField();
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.insets = new Insets(0, 0, 5, 0);
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.gridx = 2;
		gbc_textUser.gridy = 1;
		panelCentro.add(textUser, gbc_textUser);
		textUser.setColumns(12);
		
		labelPass = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_labelPass = new GridBagConstraints();
		gbc_labelPass.anchor = GridBagConstraints.EAST;
		gbc_labelPass.insets = new Insets(0, 0, 0, 5);
		gbc_labelPass.gridx = 1;
		gbc_labelPass.gridy = 2;
		panelCentro.add(labelPass, gbc_labelPass);
		
		fieldPass = new JPasswordField();
		fieldPass.setColumns(12);
		GridBagConstraints gbc_fieldPass = new GridBagConstraints();
		gbc_fieldPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldPass.gridx = 2;
		gbc_fieldPass.gridy = 2;
		panelCentro.add(fieldPass, gbc_fieldPass);
		
	}

}
