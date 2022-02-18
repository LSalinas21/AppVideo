package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends Thread{

	private JPanel panelCabecera,panelUsuario,panelLogo,panelName,panelBotones,panelAcciones;
	private JPanel panelLogReg,panelLogout,panelPremiun,panelSeccion,panelExplorador,panelMisListas;
	private JPanel panelRecientes,panelNuevaLista;
	private JLabel labelLogoPrincipal,labelUsername;
	private JButton botonLogin,botonRegistro,botonLogout,botonPremiun,botonExplorador,botonMisListas;
	private JButton botonRecientes,botonNuevaLista;
	private final JPanel panelPrincipal = new JPanel();
	private JFrame frame;

	public Principal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
	}
	public void run() {
		
		while(true) {
			
			try {
				sleep(1000);
				if(Controlador.getUnicaInstancia().getUsuarioActual() != null) {
					
					habilitaAccionesUsuario();
					//frame.revalidate();
				}else {
					deshabilitaAccionesUsuario();
					//frame.revalidate();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	private void habilitaAccionesUsuario() {
		
		botonExplorador.setEnabled(true);
		botonMisListas.setEnabled(true);
		botonRecientes.setEnabled(true);
		botonNuevaLista.setEnabled(true);
		botonLogout.setEnabled(true);
		botonPremiun.setEnabled(true);
		panelLogReg.setVisible(false);
		labelUsername.setText(Controlador.getUnicaInstancia().getUsuarioActual().getNick());
		labelUsername.setVisible(true);
		
	}
	private void deshabilitaAccionesUsuario() {
		
		botonExplorador.setEnabled(false);
		botonMisListas.setEnabled(false);
		botonRecientes.setEnabled(false);
		botonNuevaLista.setEnabled(false);
		botonLogout.setEnabled(false);
		botonPremiun.setEnabled(false);
		panelLogReg.setVisible(true);
		labelUsername.setVisible(false);
		
	}
	public void mostrarVentana() {
		
		frame.setVisible(true);
	}
	public void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		creaPanelCabecera();
		creaPanelPrincipal();
	
	}
	private void creaPanelCabecera() {
		
		panelCabecera = new JPanel();
		frame.getContentPane().add(panelCabecera, BorderLayout.NORTH);
		panelCabecera.setLayout(new BorderLayout(0, 0));
		
		creaPanelUsuario();
		creaPanelLogout();
		creaPanelPremiun();
		creaPanelSeccion();
		
		
	}
	private void creaPanelSeccion() {
		
		panelSeccion = new JPanel();
		panelCabecera.add(panelSeccion, BorderLayout.SOUTH);
		panelSeccion.setLayout(new GridLayout(1, 0, 0, 0));
		
		botonExplorador = new JButton("Explorador");
		botonExplorador.setEnabled(false);
		
		botonMisListas = new JButton("Mis Listas");
		botonMisListas.setEnabled(false);
		
		botonRecientes = new JButton("Recientes");
		botonRecientes.setEnabled(false);
		
		botonNuevaLista = new JButton("Nueva Lista");
		botonNuevaLista.setEnabled(false);
		
		configEventosBotones();
		
	}
	private void configEventosBotones() {
		
		/// Accion de botones
		
		botonExplorador.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "explorador");
			}
		});
		panelSeccion.add(botonExplorador);
		botonMisListas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "mislistas");
			}
		});
		panelSeccion.add(botonMisListas);

		botonRecientes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "recientes");
			}
		});
		panelSeccion.add(botonRecientes);
				
		botonNuevaLista.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "nuevalista");
			}
		});
		panelSeccion.add(botonNuevaLista);
		
	}
	private void creaPanelLogout() {
		
		panelLogout = new JPanel();
		panelBotones.add(panelLogout);
		
		botonLogout = new JButton("Logout");
		botonLogout.setEnabled(false);
		panelLogout.add(botonLogout);
		
		
	}
	private void creaPanelPremiun() {
		
		panelPremiun = new JPanel();
		panelBotones.add(panelPremiun);
		
		botonPremiun = new JButton("Premiun");
		botonPremiun.setEnabled(false);
		panelPremiun.add(botonPremiun);
		
	}
	private void creaPanelUsuario() {
		
		panelUsuario = new JPanel();
		panelCabecera.add(panelUsuario, BorderLayout.NORTH);
		panelUsuario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		creaPanelLogo();
		configPanelUsuario();
		
	}
	private void creaPanelLogo() {
		
		panelLogo = new JPanel();
		panelUsuario.add(panelLogo);
		
		configPanelLogo();
		
	}
	private void configPanelLogo() {
		
		labelLogoPrincipal = new JLabel("AppVideo");
		panelLogo.add(labelLogoPrincipal);
	}
	private void configPanelUsuario() {
		
		panelName = new JPanel();
		panelUsuario.add(panelName);
		panelName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelUsername = new JLabel("Username");
		panelName.add(labelUsername);
		labelUsername.setVisible(false);
		
		panelBotones = new JPanel();
		panelUsuario.add(panelBotones);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelAcciones = new JPanel();
		panelBotones.add(panelAcciones);
		panelAcciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		botonLogin = new JButton("Login");
		botonRegistro = new JButton("Registro");
		panelLogReg = new JPanel();
		panelLogReg.add(botonLogin);
		panelLogReg.add(botonRegistro);
		panelAcciones.add(panelLogReg);
		
		configEventoBotonUsuario();
		
		
	}
	private void configEventoBotonUsuario() {
		
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Registro ventanaRegistro= new Registro();
					ventanaRegistro.mostrarVentana();
				}
			});
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Login ventanaLogin= new Login();
					ventanaLogin.mostrarVentana();
				}
			});
		
	}
	private void creaPanelPrincipal() {
		
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		configPanelPrincipal();
		
	}
	private void configPanelPrincipal() {
		
		panelExplorador = new JPanel();
		panelPrincipal.add(panelExplorador, "explorador");
		
		JLabel lblNewLabel = new JLabel("Usa el boton Login para ingresear al sistema o Usa el boton Registro para darte de alta en el sistema");
		panelExplorador.add(lblNewLabel);
		
		panelMisListas = new JPanel();
		panelPrincipal.add(panelMisListas, "mislistas");
		
		JLabel lblNewLabel_1 = new JLabel("2");
		panelMisListas.add(lblNewLabel_1);
		
		panelRecientes = new JPanel();
		panelPrincipal.add(panelRecientes, "recientes");
		
		JLabel lblNewLabel_2 = new JLabel("3");
		panelRecientes.add(lblNewLabel_2);
		
		panelNuevaLista = new JPanel();
		panelPrincipal.add(panelNuevaLista, "nuevalista");
		
		JLabel lblNewLabel_3 = new JLabel("4");
		panelNuevaLista.add(lblNewLabel_3);
		
	}

}
