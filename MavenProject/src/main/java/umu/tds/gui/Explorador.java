package umu.tds.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import umu.tds.controlador.Controlador;

public class Explorador {
	
	private JPanel panel,panelVideosExplorador;
	private JScrollPane scrollPaneVideos;
	private JLabel labelBuscarTitulo,labelEtiquetasDisponibles,labelEtiquetasSeleccionadas;
	private JTextField textoBuscarTitulo;
	private JButton botonBuscar;
	private JButton botonNuevaBusqueda;
	private JList listaEtiquetasDisponibles,listEtiquetasSeleccionadas;
	
	
	public Explorador() {
		
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
	}
	private void agregaEtiquetasDeBusqueda() {
		
		labelEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(labelEtiquetasDisponibles, gbc_lblNewLabel_4);
		
		listaEtiquetasDisponibles = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 5;
		gbc_list.gridy = 2;
		panel.add(listaEtiquetasDisponibles, gbc_list);
		
		labelEtiquetasSeleccionadas = new JLabel("Buscar etiquetas");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 4;
		panel.add(labelEtiquetasSeleccionadas, gbc_lblNewLabel_5);
		
		listEtiquetasSeleccionadas = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 5;
		gbc_list_1.gridy = 5;
		panel.add(listEtiquetasSeleccionadas, gbc_list_1);
	}
	private void agregaPanelVideos() {
		
		panelVideosExplorador = new JPanel();
		panelVideosExplorador.setLayout(new FlowLayout());
		panelVideosExplorador.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelVideosExplorador.setPreferredSize(new Dimension(50,50));
		
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
	private void eventoBotonBuscar() {
		
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mostrarVideosDeBusqueda(textoBuscarTitulo.getText());
			 }
			
		});
		
	}
	private void mostrarVideosDeBusqueda(String nombre) {
		
		List<String> videos;
		videos = Controlador.getUnicaInstancia().buscarVideos(nombre);
		String[] data = new String[videos.size()];
		for(int i=0; i< videos.size(); i++) {
			
			data[i] = videos.get(i);
		}
		JList<String> videosBuscados = new JList<String>(data);
		
		/*videosBuscados.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						String nombreVideo = item.toString();
						
						String[] d = Controlador.getUnicaInstancia().getEtiquetasVideo(nombreVideo);
						
						DefaultListModel modelo = new DefaultListModel();
						for(int i = 0; i < d.length; i++) {
							
							modelo.addElement(d[i]);
						}
						
						etiquetasVideo.setModel(modelo);
						
					}
				}
			}
		});*/
	
		scrollPaneVideos.setViewportView(videosBuscados);
		
	}

}
