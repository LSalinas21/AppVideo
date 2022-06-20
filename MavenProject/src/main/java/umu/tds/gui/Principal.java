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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import javax.swing.ImageIcon;
import java.awt.Component;

public class Principal<E> extends Thread {

	private JPanel panelCabecera,panelUsuario,panelLogo,panelName,panelBotones,panelAcciones;
	private JPanel panelLogReg,panelLogout,panelPremiun,panelSeccion;
	private JLabel labelLogoPrincipal,labelUsername;
	private JButton botonLogin,botonRegistro,botonLogout,botonPremiun,botonExplorador,botonMisListas;
	private JButton botonRecientes,botonNuevaLista;
	private final JPanel panelPrincipal = new JPanel();
	private JFrame frame;
	private Explorador panelExplorador;
	private MisListas panelMisListas;
	private Recientes panelRecientes;
	private NuevaLista panelNuevaLista;
	private TopTen panelTopTen;
	private boolean band = false;
	private JPanel panelAccionesPremiun;
	private JButton botonPDF;
	private Luz botonCargador;
	private JFileChooser filechooser;
	private JComboBox filtros;
	private JButton botonTopTen;

	public Principal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
	}
	public void run() {
	
		while(true) {
			
			try {
				sleep(1000);
				if(Controlador.getUnicaInstancia().getUsuarioActual() != null) {
					
					habilitaAccionesUsuario();
					if(band == false) {
						
						configPanelPrincipal();
						band = true;
						
					}
					if(!panelPrincipal.isVisible()) {
						
						panelPrincipal.setVisible(true);
					}
					if(Controlador.getUnicaInstancia().getUsuarioActual().getPremiun()) {
						
						panelAccionesPremiun.setVisible(true);
						botonPremiun.setEnabled(false);
						botonTopTen.setEnabled(true);
					}else {
						
						panelAccionesPremiun.setVisible(false);
						botonPremiun.setEnabled(true);
						botonTopTen.setEnabled(false);
					}
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
		filtros.setSelectedItem(Controlador.getUnicaInstancia().getFiltroActivo());
		
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
		botonTopTen.setEnabled(false);
		panelAccionesPremiun.setVisible(false);
		
	}
	public void mostrarVentana() {
		
		frame.setVisible(true);
	}
	public void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		frame = new JFrame();
		frame.setSize(new Dimension(700,500));
		frame.setMinimumSize(new Dimension(650,450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		creaPanelCabecera();
		creaPanelPrincipal();
		agregarEventoCargarVideos();
		agregaEventoPremiun();
		eventoGeneraPDF();
	
	}
	private void agregaEventoPremiun() {
		
		botonPremiun.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				Controlador.getUnicaInstancia().setPremiun();
			}
		});
		
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
				panelExplorador.actualizar();
			}
		});
		panelSeccion.add(botonExplorador);
		botonMisListas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				panelMisListas.actualizar();
				cl.show(panelPrincipal, "mislistas");
			}
		});
		panelSeccion.add(botonMisListas);

		botonRecientes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "recientes");
				panelRecientes.actualizar();
			}
		});
		panelSeccion.add(botonRecientes);
				
		botonNuevaLista.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
				cl.show(panelPrincipal, "nuevalista");
				panelNuevaLista.actualizar();
			}
		});
		panelSeccion.add(botonNuevaLista);
		
		botonTopTen = new JButton("Top 10");
		botonTopTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
					cl.show(panelPrincipal, "topten");
					panelTopTen.actualizar();
				}
			});
		panelSeccion.add(botonTopTen);
		botonTopTen.setEnabled(false);
		
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
		
		labelLogoPrincipal = new JLabel("");
		labelLogoPrincipal.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/imagenes/logoAppVideo3.png")));
		labelLogoPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelLogoPrincipal.setSize(new Dimension(10, 10));
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
		
		panelAccionesPremiun = new JPanel();
		panelUsuario.add(panelAccionesPremiun);
		panelAccionesPremiun.setVisible(false);
		
		botonPDF = new JButton("PDF");
		panelAccionesPremiun.add(botonPDF);
		
		botonCargador = new Luz();
		panelAccionesPremiun.add(botonCargador);
		
		filtros = new JComboBox();
		panelAccionesPremiun.add(filtros);
		filtros.setModel(new DefaultComboBoxModel(Controlador.getUnicaInstancia().getFiltros()));
		
		filechooser = new JFileChooser();
		
		configEventoBotonUsuario();
		eventosFiltros();
		
		
	}
	private void eventoGeneraPDF() {
		
		botonPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Controlador.getUnicaInstancia().generarPDF(frame);
				}
			});
	}
	private void eventosFiltros() {
		
		filtros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					Controlador.getUnicaInstancia().setFiltroUsuario(e.getItem().toString());
						
				}
			}
		});
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
						
						CardLayout cl = (CardLayout)(panelPrincipal.getLayout());
						cl.show(panelPrincipal, "explorador");
						deshabilitaAccionesUsuario();
						panelExplorador.limpiarPanel();
						panelPrincipal.setVisible(false);
					} 
				}
				
			});
		
	}
	private void creaPanelPrincipal() {
		
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		//aqui
		
	}

	private void configPanelPrincipal() {
		
		panelExplorador = new Explorador();
		panelPrincipal.add(panelExplorador.getInstancia(),"explorador");
		
		panelMisListas = new MisListas();
		panelPrincipal.add(panelMisListas.getInstancia(), "mislistas");
		
		
		
		panelRecientes = new Recientes();
		panelPrincipal.add(panelRecientes.getInstancia(), "recientes");
		
		
		panelNuevaLista = new NuevaLista();
		panelPrincipal.add(panelNuevaLista.getInstancia(), "nuevalista");
		
		panelTopTen = new TopTen();
		panelPrincipal.add(panelTopTen.getInstancia(), "topten");

		
	}
	private void agregarEventoCargarVideos() {
		
		botonCargador.addEncendidoListener(new IEncendidoListener(){

			public void enteradoCambioEncendido(EventObject ev) {
				
				int returnVal = filechooser.showOpenDialog(panelPrincipal);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					try {
						String videoFilePath = file.getAbsolutePath();

						if(Controlador.getUnicaInstancia().cargarVideosDesdeFichero(videoFilePath)) {
							
							JOptionPane.showMessageDialog(frame, "Vieos cargadas satisfactoriamente",
									"Carga correcta", JOptionPane.INFORMATION_MESSAGE);
						}else {
							
							JOptionPane.showMessageDialog(frame,
									"El fichero seleccionado no es compatible con el formato necesario o esta repetido",
									"Fichero no válido", JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception e) {
						System.out.println("problem accessing file " + file.getAbsolutePath());

					}

					System.out.println("Canciones añadidas correctamente");

				} else {
					System.out.println("File access canceled by user.");

				}
				
			}

		});
	}


}
