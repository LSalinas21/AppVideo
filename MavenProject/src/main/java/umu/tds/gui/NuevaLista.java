package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;

public class NuevaLista {
	
	private JPanel panel,panelIzquierdo,panelDerecho;
	private JSplitPane splitPane;
	private JLabel labelNombre,labelBuscarTitulo;
	private JTextField textFieldNombre,textFieldBuscarTitulo;
	private JButton botonBuscarLista,botonBorrarLista,botonAñadir,botonAceptar,botonQuitar,botonNuevaBusqueda,botonBuscarVideo;
	private JScrollPane panelListaVideos,panelVideosBuscados;
	private DefaultListModel modeloVideos,modeloListaVideos;
	private JList videosBuscados,videosPlaylist;
	private String videoSeleccionado, videoSeleccionadoQuitar, listaActual;
	private List <String> misListas;

	
	public NuevaLista() {
		
		videosBuscados = new JList();
		modeloVideos = new DefaultListModel();
		
		videosPlaylist = new JList();
		modeloListaVideos = new DefaultListModel();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		agregaSplitPane();
		agregaPanelIzquierda();
		agregaPanelDerecho();
		agregaLabels();
		agregaTextFields();
		agregaBotones();
		agregaPanelListaDeVideos();
		agregaPanelVideosBuscados();
		agregaEventoAñadir();
		agregaEventoQuitar();
		agregaEventoAceptar();
		agregaEventoBorrar();
		

	}
	public JPanel getInstancia() {
		
		return panel;
	}
	private void agregaSplitPane() {
		
		splitPane = new JSplitPane();
		panel.add(splitPane);
	}
	private void agregaPanelIzquierda() {
		
		panelIzquierdo = new JPanel();
		splitPane.setLeftComponent(panelIzquierdo);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {10, 86, 0, 30, 0};
		gbl_panel_3.rowHeights = new int[] {5, 0, 0, 0, 30, 30, 30, 30, 30, 0, 0, 30, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelIzquierdo.setLayout(gbl_panel_3);
	}
	private void agregaLabels() {
		
		labelNombre = new JLabel("  Nombre de la lista:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		panelIzquierdo.add(labelNombre, gbc_lblNewLabel_3);
		
		labelBuscarTitulo = new JLabel("Buscar titulo:");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 1;
		gbc_lblNewLabel_13.gridy = 1;
		panelDerecho.add(labelBuscarTitulo, gbc_lblNewLabel_13);
	}
	private void agregaTextFields() {
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panelIzquierdo.add(textFieldNombre, gbc_textField_2);
		textFieldNombre.setColumns(10);
		
		textFieldBuscarTitulo = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 1;
		panelDerecho.add(textFieldBuscarTitulo, gbc_textField_3);
		textFieldBuscarTitulo.setColumns(10);
	}
	private void agregaBotones() {
		
		botonBuscarLista = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 2;
		panelIzquierdo.add(botonBuscarLista, gbc_btnNewButton_2);
		
		agregaEventoBusquedaDeLista();
		
		botonBorrarLista = new JButton("Borrar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 3;
		panelIzquierdo.add(botonBorrarLista, gbc_btnNewButton_5);
		
		botonBorrarLista.setEnabled(false);
		
		botonAñadir = new JButton("Añadir");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 9;
		panelIzquierdo.add(botonAñadir, gbc_btnNewButton_3);
		
		botonAñadir.setEnabled(false);
		
		botonQuitar = new JButton("Quitar");
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitar.gridx = 2;
		gbc_btnQuitar.gridy = 9;
		panelIzquierdo.add(botonQuitar, gbc_btnQuitar);
		
		botonQuitar.setEnabled(false);
		
		botonAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 10;
		panelIzquierdo.add(botonAceptar, gbc_btnNewButton_4);
		
		botonAceptar.setEnabled(false);
		
		botonNuevaBusqueda = new JButton("Nueva Busqueda");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 1;
		gbc_btnNewButton_7.gridy = 2;
		panelDerecho.add(botonNuevaBusqueda, gbc_btnNewButton_7);
		
		agregaEventoNuevaBusqueda();
		
		botonBuscarVideo = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 4;
		gbc_btnNewButton_6.gridy = 2;
		panelDerecho.add(botonBuscarVideo, gbc_btnNewButton_6);
		
		agregaEventoBuscarVideo();
	}
	private void agregaPanelListaDeVideos() {
		
		panelListaVideos = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.ipadx = 10;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.gridheight = 5;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 4;
		panelIzquierdo.add(panelListaVideos, gbc_scrollPane_3);
	}
	private void agregaPanelVideosBuscados() {
		
		panelVideosBuscados = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridwidth = 4;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 3;
		panelDerecho.add(panelVideosBuscados, gbc_scrollPane_4);
	}
	private void agregaPanelDerecho() {
		
		panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {30, 0, 10, 30, 0, 30, 0};
		gbl_panel_4.rowHeights = new int[]{5, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelDerecho.setLayout(gbl_panel_4);
	}
	private void agregaEventoBuscarVideo() {
		
		botonBuscarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarVideosDeBusqueda(textFieldBuscarTitulo.getText());
			 }
			
		});
	}
	private void agregaEventoAceptar() {
		
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Desea guardar esta lista?", "¿Desea guardar esta lista?", JOptionPane.YES_NO_OPTION);
				if(resp == JOptionPane.YES_OPTION) {
					
					Controlador.getUnicaInstancia().crearPlayList(listaActual, misListas);
					botonAceptar.setEnabled(false);
				}
			}
			
		});
		
	}
	private void agregaEventoBorrar() {
		
		botonBorrarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Desea borrar la lista " + listaActual +"?",
									"¿Desea borrar la lista " + listaActual +"?", JOptionPane.YES_NO_OPTION);
				
				if(resp == JOptionPane.YES_OPTION) {
					
					textFieldNombre.setText("");
					modeloListaVideos.removeAllElements();
					botonAñadir.setEnabled(false);
					botonBorrarLista.setEnabled(false);
					botonQuitar.setEnabled(false);
					botonAceptar.setEnabled(false);
					Controlador.getUnicaInstancia().borrarPlayList(listaActual);
	
				}
			 }
			
		});
		
	}
	private void agregaEventoAñadir() {
		
		botonAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!misListas.contains(videoSeleccionado)) {
					
					modeloListaVideos.addElement(videoSeleccionado);
					misListas.add(videoSeleccionado);
					botonAceptar.setEnabled(true);
				}
			}
		});
		panelListaVideos.setViewportView(videosPlaylist);
		
	}
	private void agregaEventoQuitar() {
		
		botonQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(videoSeleccionadoQuitar != null) {
					
					modeloListaVideos.removeElement(videoSeleccionadoQuitar);
					misListas.remove(videoSeleccionadoQuitar);
					videoSeleccionadoQuitar = "";
				}
			}
		});
		panelListaVideos.setViewportView(videosPlaylist);
		
	}
	private void mostrarVideosDeBusqueda(String nombre) {
		
		modeloVideos.removeAllElements();
		List<String> videos;
		
		videos = Controlador.getUnicaInstancia().buscarVideos(nombre);
	
		for(String nVi: videos) {
			
			modeloVideos.addElement(nVi);
		}
		
		videosBuscados.setModel(modeloVideos);

		videosBuscados.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 2) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						String nombreVideo = item.toString();
						
						Video vid = Controlador.getUnicaInstancia().getVideo(nombreVideo);
						Controlador.getUnicaInstancia().reproducir(vid.getTitulo(), vid.getUrl());
						
					}
				}else if(me.getClickCount() == 1) {
					
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						
						Object item = target.getModel().getElementAt(index);
						String nombreVideo = item.toString();
						videoSeleccionado = nombreVideo;
						
					}
					
				}
				
			}
		});
	
		panelVideosBuscados.setViewportView(videosBuscados);
		
	}
	private void eventoBotonNuevaBusqueda() {
		
		botonNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JList<String> videosBuscados = new JList<String>();
				panelVideosBuscados.setViewportView(videosBuscados);
				textFieldBuscarTitulo.setText("");
			 }
			
		});
		
	}
	private void agregaEventoNuevaBusqueda() {
		
		botonNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eventoBotonNuevaBusqueda();
			}
		});
	}
	private void agregaEventoBusquedaDeLista() {
		
		botonBuscarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreLista = textFieldNombre.getText();

				if(!nombreLista.equals("")) {
					
					botonBorrarLista.setEnabled(true);
					botonQuitar.setEnabled(true);
					botonAñadir.setEnabled(true);
					botonAceptar.setEnabled(false);
					busquedaDeListas(nombreLista);
					
					
				}
					
			
			}
		});
		
	}
	private void busquedaDeListas(String nombre) {
		
		listaActual = nombre;
		modeloListaVideos.removeAllElements();
		misListas = Controlador.getUnicaInstancia().getLista(nombre);
		
		videosPlaylist.setModel(modeloListaVideos);
		
		if(misListas.size() != 0) {
			
			for(String titu: misListas)
				modeloListaVideos.addElement(titu);
		}else {
			
			botonAceptar.setEnabled(true);
			botonBorrarLista.setEnabled(false);
		}
			
		
		
		videosPlaylist.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 2) {
					
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						String nombreVideo = item.toString();
						
						Video vid = Controlador.getUnicaInstancia().getVideo(nombreVideo);
						Controlador.getUnicaInstancia().reproducir(vid.getTitulo(), vid.getUrl());
					}
				}else if (me.getClickCount() == 1) {
					
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);

						videoSeleccionadoQuitar = item.toString();
						
						
					}
				}
			}
		});

		panelListaVideos.setViewportView(videosPlaylist);
	}

}
