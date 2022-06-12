package umu.tds.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputAdapter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;

public class Explorador {
	
	private JPanel panel,panelVideosExplorador,panelEtiquetasDis,panelEtiquetasSelec;
	private JScrollPane scrollPaneVideos,scrollPaneEtiquetasDis,scrollPaneEtiquetasSelec;
	private JLabel labelBuscarTitulo,labelEtiquetasDisponibles,labelEtiquetasSeleccionadas;
	private JTextField textoBuscarTitulo;
	private JButton botonBuscar;
	private JButton botonNuevaBusqueda;
	private JList listaEtiquetasDisponibles,listEtiquetasSeleccionadas;
	private List<String> selec;
	private DefaultListModel modeloDis,modeloVideos;
	private JList allEti,videosBuscados;
	
	
	public Explorador() {
		
		videosBuscados = new JList();
		panel = new JPanel();
		
		GridBagLayout gbl_panelExplorador = new GridBagLayout();
		gbl_panelExplorador.columnWidths = new int[]{25, 0, 0, 0, 27, 0, 0};
		gbl_panelExplorador.rowHeights = new int[]{39, 0, 93, 0, 27, 116, 0};
		gbl_panelExplorador.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelExplorador.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panelExplorador);
		
		agregaBuscarTitulo();
		agregaEtiquetasDeBusqueda();
		agregaPanelVideos();
		
		agregaPanelEtiquetasDis();
		agregaPanelEtiqeutasSelec();
		llenarEtiquetasDisponibles();
		
	}
	public JPanel getInstancia() {
		
		return panel;
	}
	private void agregaBuscarTitulo() {
		
		labelBuscarTitulo = new JLabel("Buscar titulo: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(labelBuscarTitulo, gbc_lblNewLabel);
		
		textoBuscarTitulo = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel.add(textoBuscarTitulo, gbc_textField);
		textoBuscarTitulo.setColumns(10);
		
		botonBuscar = new JButton("Buscar");
		eventoBotonBuscar();
		
		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscar.gridx = 3;
		gbc_botonBuscar.gridy = 0;
		panel.add(botonBuscar, gbc_botonBuscar);
		
		botonNuevaBusqueda = new JButton("Nueva Busqueda");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(botonNuevaBusqueda, gbc_btnNewButton_1);
		
		eventoBotonNuevaBusqueda();
	}
	private void agregaEtiquetasDeBusqueda() {
		
		labelEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(labelEtiquetasDisponibles, gbc_lblNewLabel_4);
		
		labelEtiquetasSeleccionadas = new JLabel("Buscar etiquetas");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 4;
		panel.add(labelEtiquetasSeleccionadas, gbc_lblNewLabel_5);
		
		
	}
	private void agregaPanelVideos() {
		
		panelVideosExplorador = new JPanel();
		
		GridBagConstraints gbc_panelVideosExplorador = new GridBagConstraints();
		gbc_panelVideosExplorador.gridwidth = 3;
		gbc_panelVideosExplorador.gridheight = 4;
		gbc_panelVideosExplorador.insets = new Insets(0, 0, 5, 5);
		gbc_panelVideosExplorador.fill = GridBagConstraints.BOTH;
		gbc_panelVideosExplorador.gridx = 1;
		gbc_panelVideosExplorador.gridy = 2;
		
		scrollPaneVideos = new JScrollPane();
		scrollPaneVideos.setPreferredSize(new Dimension(441, 269));
		panelVideosExplorador.add(scrollPaneVideos);
		panel.add(panelVideosExplorador, gbc_panelVideosExplorador);
		
		
	}
	private void agregaPanelEtiquetasDis() {
		
		panelEtiquetasDis = new JPanel();
		
		GridBagConstraints gbc_EtiquetasDis = new GridBagConstraints();
		gbc_EtiquetasDis.gridheight = 2;
		gbc_EtiquetasDis.insets = new Insets(0, 0, 5, 0);
		gbc_EtiquetasDis.fill = GridBagConstraints.BOTH;
		gbc_EtiquetasDis.gridx = 5;
		gbc_EtiquetasDis.gridy = 2;
		
		scrollPaneEtiquetasDis = new JScrollPane();
		scrollPaneEtiquetasDis.setPreferredSize(new Dimension(110, 125));
		panelEtiquetasDis.add(scrollPaneEtiquetasDis);
		panel.add(panelEtiquetasDis, gbc_EtiquetasDis);
	}
	private void agregaPanelEtiqeutasSelec() {
		
		panelEtiquetasSelec = new JPanel();
		GridBagConstraints gbc_EtiquetasSele = new GridBagConstraints();
		gbc_EtiquetasSele.fill = GridBagConstraints.BOTH;
		gbc_EtiquetasSele.gridx = 5;
		gbc_EtiquetasSele.gridy = 5;
		
		scrollPaneEtiquetasSelec = new JScrollPane();
		scrollPaneEtiquetasSelec.setPreferredSize(new Dimension(110, 125));
		panelEtiquetasSelec.add(scrollPaneEtiquetasSelec);
		panel.add(panelEtiquetasSelec, gbc_EtiquetasSele);
		
		panel.add(panelEtiquetasSelec, gbc_EtiquetasSele);

		//scrollPaneEtiquetasSelec.setViewportView(listEtiquetasSeleccionadas);
		
		
	}
	private void eventoBotonBuscar() {
		
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarVideosDeBusqueda(textoBuscarTitulo.getText());
			 }
			
		});
		
	}
	private void eventoBotonNuevaBusqueda() {
		
		botonNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JList<String> videosBuscados = new JList<String>();
				scrollPaneVideos.setViewportView(videosBuscados);
				textoBuscarTitulo.setText("");
			 }
			
		});
		
	}
	private void mostrarVideosDeBusqueda(String nombre) {
		
		List<String> videos;
		
		modeloVideos = new DefaultListModel();
		
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
				}
			}
		});
	
		scrollPaneVideos.setViewportView(videosBuscados);
		
	}
	private void mostrarVideosDeBusquedaPorEtiqueta() {
		
		DefaultListModel modeloVideosPorEti = new DefaultListModel();
		
		List<String> videosEti = Controlador.getUnicaInstancia().buscarVideosPorEtiquetas(selec);
		
		videosBuscados.setModel(modeloVideosPorEti);
		for(String titu: videosEti)
			modeloVideosPorEti.addElement(titu);
		
		scrollPaneVideos.setViewportView(videosBuscados);
		
	}

	private void llenarEtiquetasDisponibles() {
		
		selec = new ArrayList<String>();
		
		modeloDis = new DefaultListModel();
		
		allEti = new JList();
		allEti.setModel(modeloDis);
		
		for(String eti: Controlador.getUnicaInstancia().getEtiquetas())
			modeloDis.addElement(eti);
		
		allEti.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						
						Object item = target.getModel().getElementAt(index);
						String nombreEtiqueta = item.toString();
						
						if(!selec.contains(nombreEtiqueta)) {
							
							selec.add(nombreEtiqueta);
						
							agregarEtiquetasSeleccionadas();
							mostrarVideosDeBusquedaPorEtiqueta();
							
						}
						
					}
				}
			}

		});
		scrollPaneEtiquetasDis.setViewportView(allEti);
		
	}
	private void agregarEtiquetasSeleccionadas() {
		
		DefaultListModel modelo = new DefaultListModel();
		JList lista = new JList();
		lista.setModel(modelo);
		
		for(String et: selec)
			modelo.addElement(et);
		
		lista.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					
					if (index >= 0) {
						
						Object item = target.getModel().getElementAt(index);
						modelo.removeElement(item);
						selec.remove(item.toString());
						mostrarVideosDeBusquedaPorEtiqueta();
						
					}
				}
			}

		});
		scrollPaneEtiquetasSelec.setViewportView(lista);
		
	}

	public void actualizar() {
		
		llenarEtiquetasDisponibles();
		
	}
}
