package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Video;
import umu.tds.herramientas.JListRenderer;

public class TopTen {

	private JPanel panel,panelTopTen;
	private JLabel labelTopTen;
	private JScrollPane scrollPaneTopTen;
	private JList videosTopTen;
	
	public TopTen() {
		
		videosTopTen = new JList();
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		agregaLabels();
		agregaPanelTopTen();
		mostrarVideosTopTen();
	}
	public JPanel getInstancia() {
		
		return panel;
	}
	private void agregaLabels() {
		
		labelTopTen = new JLabel("10 videos más vistos");
		labelTopTen.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelTopTen, BorderLayout.NORTH);
	}
	private void agregaPanelTopTen() {
		
		scrollPaneTopTen = new JScrollPane();
		panel.add(scrollPaneTopTen, BorderLayout.CENTER);
		
		panelTopTen = new JPanel();
		scrollPaneTopTen.setViewportView(panelTopTen);
		panelTopTen.setLayout(new GridLayout(0, 1, 0, 0));
	}
	public void actualizar() {
		
		mostrarVideosTopTen();
	}
	private void mostrarVideosTopTen() {
		
		List<Video> videos = Controlador.getUnicaInstancia().getTopTen();
		videosTopTen = JListRenderer.getInstancia().getListaR(videos);
		
		videosTopTen.addMouseListener(new MouseInputAdapter() {
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
						Controlador.getUnicaInstancia().addReciente(vid);
						
					}
				}
			}
		});
	
		scrollPaneTopTen.setViewportView(videosTopTen);
		
	}

}

