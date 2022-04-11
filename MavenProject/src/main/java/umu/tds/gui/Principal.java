package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Principal extends Thread{

	private JPanel panelCabecera,panelUsuario,panelLogo,panelName,panelBotones,panelAcciones;
	private JPanel panelLogReg,panelLogout,panelPremiun,panelSeccion,panelExplorador,panelMisListas;
	private JPanel panelRecientes,panelNuevaLista;
	private JLabel labelLogoPrincipal,labelUsername;
	private JButton botonLogin,botonRegistro,botonLogout,botonPremiun,botonExplorador,botonMisListas;
	private JButton botonRecientes,botonNuevaLista;
	private final JPanel panelPrincipal = new JPanel();
	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton botonBuscar;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_4;
	private JList list;
	private JLabel lblNewLabel_5;
	private JList list_1;
	private JScrollPane scrollPane;
	private JPanel panelDeBusqueda;

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
		
		configEventoBotonLogOut();
		
		
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
	private void configEventoBotonLogOut() {
		
		botonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					boolean loginOut = Controlador.getUnicaInstancia().loginOutUsuario();

					if (loginOut) {
						
						deshabilitaAccionesUsuario();
					} 
				}
				
			});
		
	}
	private void creaPanelPrincipal() {
		
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		configPanelPrincipal();
		
	}
	private void eventoBotonBuscar() {
		
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JLabel labels[] = new JLabel[200];
			    for (int i =  0; i < 200; i++) {
			       labels[i] = new JLabel("Label" + i);
			       panelDeBusqueda.add(labels[i]);
			 }
			}
		});
		
	}
	private void configPanelPrincipal() {
		
		panelExplorador = new JPanel();
		panelPrincipal.add(panelExplorador, "explorador");
		GridBagLayout gbl_panelExplorador = new GridBagLayout();
		gbl_panelExplorador.columnWidths = new int[]{25, 0, 0, 0, 27, 0, 0};
		gbl_panelExplorador.rowHeights = new int[]{39, 0, 93, 0, 0, 116, 0};
		gbl_panelExplorador.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelExplorador.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelExplorador.setLayout(gbl_panelExplorador);
		
		lblNewLabel = new JLabel("Buscar titulo: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panelExplorador.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panelExplorador.add(textField, gbc_textField);
		textField.setColumns(10);
		
		
		botonBuscar = new JButton("Buscar");
		eventoBotonBuscar();
		
		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscar.gridx = 3;
		gbc_botonBuscar.gridy = 0;
		panelExplorador.add(botonBuscar, gbc_botonBuscar);
		
		btnNewButton_1 = new JButton("Nueva Busqueda");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panelExplorador.add(btnNewButton_1, gbc_btnNewButton_1);
		
		lblNewLabel_4 = new JLabel("Etiquetas disponibles");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panelExplorador.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		scrollPane = new JScrollPane();

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;

		
		panelExplorador.add(scrollPane, gbc_scrollPane);
		
		list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 5;
		gbc_list.gridy = 2;
		panelExplorador.add(list, gbc_list);
		
		lblNewLabel_5 = new JLabel("Buscar etiquetas");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 4;
		panelExplorador.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 5;
		gbc_list_1.gridy = 5;
		panelExplorador.add(list_1, gbc_list_1);
		
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
