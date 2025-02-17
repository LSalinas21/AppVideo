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
import javax.swing.ImageIcon;
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
import umu.tds.herramientas.JListRenderer;

public class Explorador {
	
	private JPanel panel,panelVideosExplorador,panelEtiquetasDis,panelEtiquetasSelec;
	private JScrollPane scrollPaneVideos,scrollPaneEtiquetasDis,scrollPaneEtiquetasSelec;
	private JLabel labelBuscarTitulo,labelEtiquetasDisponibles,labelEtiquetasSeleccionadas;
	private JTextField textoBuscarTitulo;
	private JButton botonBuscar;
	private JButton botonNuevaBusqueda;
	private List<String> selec;
	private DefaultListModel modeloDis;
	private JList allEti,videosBuscados;
	
	
	public Explorador() {
		
		videosBuscados = new JList();
		panel = new JPanel();
		
		GridBagLayout gbl_panelExplorador = new GridBagLayout();
		gbl_panelExplorador.columnWidths = new int[]{25, 0, 0, 0, 31, 76, 0};
		gbl_panelExplorador.rowHeights = new int[]{39, 0, 93, 0, 0, 27, 9, 0};
		gbl_panelExplorador.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelExplorador.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		GridBagConstraints gbc_iconoVideo = new GridBagConstraints();
		gbc_iconoVideo.insets = new Insets(0, 0, 5, 5);
		gbc_iconoVideo.anchor = GridBagConstraints.EAST;
		gbc_iconoVideo.gridx = 1;
		gbc_iconoVideo.gridy = 0;
		panel.add(labelBuscarTitulo, gbc_iconoVideo);
		
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
		
		
	}
	private void agregaPanelVideos() {
		
		panelVideosExplorador = new JPanel();
		
		GridBagConstraints gbc_panelVideosExplorador = new GridBagConstraints();
		gbc_panelVideosExplorador.gridwidth = 4;
		gbc_panelVideosExplorador.gridheight = 5;
		gbc_panelVideosExplorador.insets = new Insets(0, 0, 0, 5);
		gbc_panelVideosExplorador.fill = GridBagConstraints.BOTH;
		gbc_panelVideosExplorador.gridx = 1;
		gbc_panelVideosExplorador.gridy = 2;
		
		scrollPaneVideos = new JScrollPane();
		panel.add(scrollPaneVideos, gbc_panelVideosExplorador);
		
		
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
		panel.add(scrollPaneEtiquetasDis, gbc_EtiquetasDis);
	}
	private void agregaPanelEtiqeutasSelec() {
		
		panelEtiquetasSelec = new JPanel();
		
		labelEtiquetasSeleccionadas = new JLabel("Buscar etiquetas");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 4;
		panel.add(labelEtiquetasSeleccionadas, gbc_lblNewLabel_5);
		GridBagConstraints gbc_EtiquetasSele = new GridBagConstraints();
		gbc_EtiquetasSele.insets = new Insets(0, 0, 5, 0);
		gbc_EtiquetasSele.fill = GridBagConstraints.BOTH;
		gbc_EtiquetasSele.gridx = 5;
		gbc_EtiquetasSele.gridy = 5;
		
		scrollPaneEtiquetasSelec = new JScrollPane();
		
		panel.add(scrollPaneEtiquetasSelec, gbc_EtiquetasSele);
		
		
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
				
				limpiarPanel();
			 }
			
		});
		
	}
	private void mostrarVideosDeBusqueda(String nombre) {
		
		List<Video> videos = Controlador.getUnicaInstancia().buscarVideos(nombre,selec);
		
		videosBuscados = JListRenderer.getInstancia().getListaR(videos);
		
		videosBuscados.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 2) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Video vid = (Video) item;
						Controlador.getUnicaInstancia().addReciente(vid);
						Controlador.getUnicaInstancia().reproducir(vid.getTitulo(), vid.getUrl());
						Controlador.getUnicaInstancia().agregarReproduccion(vid);
						
					}
				}
			}
		});
	
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
						
					}
				}
			}

		});
		scrollPaneEtiquetasSelec.setViewportView(lista);
		
	}

	public void actualizar() {
		
		llenarEtiquetasDisponibles();
		limpiarSeleccionadas();
		limpiarPanel();
		
	}
	public void limpiarSeleccionadas() {
		
		selec.clear();
		agregarEtiquetasSeleccionadas();
		
	}
	public void limpiarPanel() {
		
		JList<String> videosBuscados = new JList<String>();
		scrollPaneVideos.setViewportView(videosBuscados);
		textoBuscarTitulo.setText("");
	}
}
