package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;
import umu.tds.herramientas.JListRenderer;

public class Recientes {
	
	private JPanel panel,panelRecientes;
	private JLabel labelMasRecientes;
	private JScrollPane scrollPaneMasRecientes;
	private JList videosRecientes;
	
	public Recientes() {
		
		videosRecientes = new JList();
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		agregaLabels();
		agregaPanelRecientes();
		mostrarVideosRecientes();
	}
	public JPanel getInstancia() {
		
		return panel;
	}
	private void agregaLabels() {
		
		labelMasRecientes = new JLabel("5 videos más recientes");
		labelMasRecientes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelMasRecientes, BorderLayout.NORTH);
	}
	private void agregaPanelRecientes() {
		
		scrollPaneMasRecientes = new JScrollPane();
		panel.add(scrollPaneMasRecientes, BorderLayout.CENTER);
		
		panelRecientes = new JPanel();
		scrollPaneMasRecientes.setViewportView(panelRecientes);
		panelRecientes.setLayout(new GridLayout(0, 1, 0, 0));
	}
	public void actualizar() {
		
		mostrarVideosRecientes();
	}
	private void mostrarVideosRecientes() {
		
		List<Video> videos = Controlador.getUnicaInstancia().getRecientes();
		videosRecientes = JListRenderer.getInstancia().getListaR(videos);
		
		videosRecientes.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 2) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Video vid = (Video) item;
						Controlador.getUnicaInstancia().reproducir(vid.getTitulo(), vid.getUrl());
						Controlador.getUnicaInstancia().agregarReproduccion(vid);
						Controlador.getUnicaInstancia().addReciente(vid);
						
					}
				}
			}
		});
	
		scrollPaneMasRecientes.setViewportView(videosRecientes);
		
	}

}
