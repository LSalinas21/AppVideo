package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;

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
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JViewport;

import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

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
	private JPanel panel;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JScrollBar scrollBar;
	private JLabel buscarPlaylist;
	private JTextField textAreaBuscarMisListas;
	private JButton botonBuscarMisListas;
	private JScrollPane scrollPane_2;
	private JPanel panel_2;
	private JSplitPane splitPane;
	private JPanel panel_3;
	private JLabel lblNewLabel_3;
	private JTextField textField_2;
	private JPanel panel_4;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnQuitar;
	private JButton btnNewButton_4;
	private JScrollPane scrollPane_3;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel_13;
	private JButton btnNewButton_6;
	private JTextField textField_3;
	private JButton btnNewButton_7;
	private JScrollPane scrollPane_4;
	private JScrollBar scrollBarMisListas;
	private JPanel panelMisListasBuscadas;
	private JList lista;
	private JScrollPane panelBuscadas;

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
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		scrollBar = new JScrollBar();
		panel.add(scrollBar);
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		List<Video> lis = new ArrayList<Video>();
	/*	lis = Controlador.getUnicaInstancia().getLista();
		for (Video v: lis) {
				JLabel l = new JLabel(v.getTitulo());
				panel.add(l);	
		}
		*/

		
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
		
		
		///Panel mis listas
		
		panelMisListas = new JPanel();
		panelPrincipal.add(panelMisListas, "mislistas");
		GridBagLayout gbl_panelMisListas = new GridBagLayout();
		gbl_panelMisListas.columnWidths = new int[]{0, 50, 79, 0, 0, 50, 0, 0, 0, 0};
		gbl_panelMisListas.rowHeights = new int[]{10, 0, 0, 0, 10, 0};
		gbl_panelMisListas.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMisListas.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelMisListas.setLayout(gbl_panelMisListas);
		
		buscarPlaylist = new JLabel("Buscar playlist : ");
		GridBagConstraints gbc_buscarPlaylist = new GridBagConstraints();
		gbc_buscarPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_buscarPlaylist.anchor = GridBagConstraints.EAST;
		gbc_buscarPlaylist.gridx = 2;
		gbc_buscarPlaylist.gridy = 1;
		panelMisListas.add(buscarPlaylist, gbc_buscarPlaylist);
		
		textAreaBuscarMisListas = new JTextField();
		GridBagConstraints gbc_textAreaBuscarMisListas = new GridBagConstraints();
		gbc_textAreaBuscarMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaBuscarMisListas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaBuscarMisListas.gridx = 3;
		gbc_textAreaBuscarMisListas.gridy = 1;
		panelMisListas.add(textAreaBuscarMisListas, gbc_textAreaBuscarMisListas);
		textAreaBuscarMisListas.setColumns(10);
		
		botonBuscarMisListas = new JButton("Buscar");
		
		eventoBotonBuscarMisListas();
		
		GridBagConstraints gbc_botonBuscarMisListas = new GridBagConstraints();
		gbc_botonBuscarMisListas.anchor = GridBagConstraints.NORTH;
		gbc_botonBuscarMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscarMisListas.gridx = 4;
		gbc_botonBuscarMisListas.gridy = 1;
		panelMisListas.add(botonBuscarMisListas, gbc_botonBuscarMisListas);
		
		panelMisListasBuscadas = new JPanel();
		panelMisListasBuscadas.setLayout(new FlowLayout());
		panelMisListasBuscadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelMisListasBuscadas.setPreferredSize(new Dimension(50,50));
		
		
		///Busqueda de las lista
		
		
		GridBagConstraints gbc_panelMisListasBuscadas = new GridBagConstraints();
		gbc_panelMisListasBuscadas.insets = new Insets(0, 0, 5, 5);
		gbc_panelMisListasBuscadas.fill = GridBagConstraints.BOTH;
		gbc_panelMisListasBuscadas.gridx = 3;
		gbc_panelMisListasBuscadas.gridy = 3;
		
		panelBuscadas = new JScrollPane();
		panelBuscadas.setPreferredSize(new Dimension(350,285));
		panelMisListasBuscadas.add(panelBuscadas);
		panelMisListas.add(panelMisListasBuscadas, gbc_panelMisListasBuscadas);
		
		panelRecientes = new JPanel();
		panelPrincipal.add(panelRecientes, "recientes");
		panelRecientes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("5 videos más recientes");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelRecientes.add(lblNewLabel_2, BorderLayout.NORTH);
		
		scrollPane_2 = new JScrollPane();
		panelRecientes.add(scrollPane_2, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		scrollPane_2.setViewportView(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelNuevaLista = new JPanel();
		panelPrincipal.add(panelNuevaLista, "nuevalista");
		panelNuevaLista.setLayout(new GridLayout(1, 0, 0, 0));
		
		splitPane = new JSplitPane();
		panelNuevaLista.add(splitPane);
		
		panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {10, 86, 0, 30, 0};
		gbl_panel_3.rowHeights = new int[] {5, 0, 0, 0, 30, 30, 30, 30, 30, 0, 0, 30, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		lblNewLabel_3 = new JLabel("  Nombre de la lista:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_3.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_2 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 2;
		panel_3.add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnNewButton_5 = new JButton("Borrar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 3;
		panel_3.add(btnNewButton_5, gbc_btnNewButton_5);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.ipadx = 10;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.gridheight = 5;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 4;
		panel_3.add(scrollPane_3, gbc_scrollPane_3);
		
		btnNewButton_3 = new JButton("Añadir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 9;
		panel_3.add(btnNewButton_3, gbc_btnNewButton_3);
		
		btnQuitar = new JButton("Quitar");
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitar.gridx = 2;
		gbc_btnQuitar.gridy = 9;
		panel_3.add(btnQuitar, gbc_btnQuitar);
		
		btnNewButton_4 = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 10;
		panel_3.add(btnNewButton_4, gbc_btnNewButton_4);
		
		panel_4 = new JPanel();
		splitPane.setRightComponent(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {30, 0, 10, 30, 0, 30, 0};
		gbl_panel_4.rowHeights = new int[]{5, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		lblNewLabel_13 = new JLabel("Buscar titulo:");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 1;
		gbc_lblNewLabel_13.gridy = 1;
		panel_4.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 1;
		panel_4.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		btnNewButton_7 = new JButton("Nueva Busqueda");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 1;
		gbc_btnNewButton_7.gridy = 2;
		panel_4.add(btnNewButton_7, gbc_btnNewButton_7);
		
		btnNewButton_6 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 4;
		gbc_btnNewButton_6.gridy = 2;
		panel_4.add(btnNewButton_6, gbc_btnNewButton_6);
		
		scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridwidth = 4;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 3;
		panel_4.add(scrollPane_4, gbc_scrollPane_4);
		
	}
	private void eventoBotonBuscarMisListas() {
		
		botonBuscarMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> misListas;
				misListas = Controlador.getUnicaInstancia().buscarMisListas(textAreaBuscarMisListas.getText());
				String[] data = new String[misListas.size()];
				for(int i=0; i< misListas.size(); i++) {
					
					data[i] = misListas.get(i);
				}
				final JList<String> playlists = new JList<String>(data);
				final JList<String> videosPlaylist = new JList<String>(data);
				
				playlists.addMouseListener(new MouseInputAdapter() {
					
					public void mouseClicked(MouseEvent me) {
						
						if (me.getClickCount() == 1) {
							JList target = (JList) me.getSource();
							int index = target.locationToIndex(me.getPoint());
							if (index >= 0) {
								Object item = target.getModel().getElementAt(index);
								String nombrePlaylist = item.toString();
								
								List<String> videos = Controlador.getUnicaInstancia().getLista(nombrePlaylist);
								String[] d = new String[videos.size()];
								
								for(int i=0; i< videos.size(); i++) {
									
									d[i] = videos.get(i);
								}
								
								videosPlaylist.setListData(d);
								
							}
							panelBuscadas.setViewportView(videosPlaylist);
						}
				}});
				panelBuscadas.setViewportView(playlists);
				
			}
		});
	}


}
