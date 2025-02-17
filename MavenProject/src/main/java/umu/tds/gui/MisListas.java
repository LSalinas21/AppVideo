package umu.tds.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Video;
import umu.tds.herramientas.JListRenderer;

public class MisListas {
	
	private JPanel panel;
	private JLabel buscarPlaylist,labelNuevaEtiqueta,labelEtiquetasDeVideo;
	private JComboBox textAreaBuscarMisListas;
	private JScrollPane panelBuscadas,panelEtiquetas;
	private JTextField textFieldNuevaEtiqueta;
	private JButton botonCrear;
	private JList etiquetasVideo;
	private DefaultListModel modeloVideosLista, modeloEtiqeutasVideoSele;
	private Video videoSeleccionado;
	private JList videosPlaylist;
	
	public MisListas() {
		
		modeloVideosLista = new DefaultListModel();
		modeloEtiqeutasVideoSele = new DefaultListModel();
		panel = new JPanel();
		videosPlaylist = new JList();
		GridBagLayout gbl_panelMisListas = new GridBagLayout();
		gbl_panelMisListas.columnWidths = new int[]{10, 50, 79, 0, 0, 50, 20, 0, 0, 0};
		gbl_panelMisListas.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 0, 10, 0};
		gbl_panelMisListas.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMisListas.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panelMisListas);
		
		agregaEtiquetas();
		agregaTextArea();
		agregaPanelMisListasBuscadas();
		agregaBotones();
	}
	public JPanel getInstancia() {
		
		return panel;
	}
	public void actualizar() {
		
		textAreaBuscarMisListas.setModel(new DefaultComboBoxModel(creaSeleccionableMisListas()));
		textFieldNuevaEtiqueta.setText("");
		modeloVideosLista.removeAllElements();
		modeloEtiqeutasVideoSele.removeAllElements();
		limpiarPanel();
		
	}
	private String[] creaSeleccionableMisListas() {
		
		String[] plist = new String[Controlador.getUnicaInstancia().getPlayList().size() +1];
		
		plist[0] = "---Selecciona---";
		int i = 1;
		for(PlayList pl: Controlador.getUnicaInstancia().getPlayList()) {
			
			plist[i] = pl.getNombre();
			i++;
		}
		return plist;
	}
	private void agregaEtiquetas() {
		
		buscarPlaylist = new JLabel("Seleccionar playlist : ");
		GridBagConstraints gbc_buscarPlaylist = new GridBagConstraints();
		gbc_buscarPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_buscarPlaylist.anchor = GridBagConstraints.EAST;
		gbc_buscarPlaylist.gridx = 1;
		gbc_buscarPlaylist.gridy = 1;
		panel.add(buscarPlaylist, gbc_buscarPlaylist);
		
		labelNuevaEtiqueta = new JLabel("Nueva etiqueta");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_14.gridx = 3;
		gbc_lblNewLabel_14.gridy = 2;
		panel.add(labelNuevaEtiqueta, gbc_lblNewLabel_14);
		
		textFieldNuevaEtiqueta = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		panel.add(textFieldNuevaEtiqueta, gbc_textField_1);
		textFieldNuevaEtiqueta.setColumns(10);
		
		botonCrear = new JButton("Crear");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 2;
		panel.add(botonCrear, gbc_btnNewButton);
		
		labelEtiquetasDeVideo = new JLabel("Etiquetas");
		GridBagConstraints gbc_labelEtiquetasDeVideo = new GridBagConstraints();
		gbc_labelEtiquetasDeVideo.insets = new Insets(0, 0, 5, 5);
		gbc_labelEtiquetasDeVideo.gridx = 5;
		gbc_labelEtiquetasDeVideo.gridy = 3;
		panel.add(labelEtiquetasDeVideo, gbc_labelEtiquetasDeVideo);
		
		panelEtiquetas = new JScrollPane();
		panelEtiquetas.setPreferredSize(new Dimension(100,100));
		
		etiquetasVideo = new JList();
		
		GridBagConstraints gbc_etiquetasVideo = new GridBagConstraints();
		gbc_etiquetasVideo.gridheight = 2;
		gbc_etiquetasVideo.gridwidth = 4;
		gbc_etiquetasVideo.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetasVideo.fill = GridBagConstraints.BOTH;
		gbc_etiquetasVideo.gridx = 4;
		gbc_etiquetasVideo.gridy = 4;
		
		panelEtiquetas.add(etiquetasVideo);
		panel.add(panelEtiquetas, gbc_etiquetasVideo);
	}
	private void agregaTextArea() {
		
		textAreaBuscarMisListas = new JComboBox();
		textAreaBuscarMisListas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					if(e.getItem().toString() != "---Selecciona---") {
						
						modeloVideosLista.removeAllElements();
						mostrarVideosLista(e.getItem().toString());
					}else {
						
						videosMisListasBlanco();
					}
						
				}
			}
		});
		textAreaBuscarMisListas.setModel(new DefaultComboBoxModel(creaSeleccionableMisListas()));
		GridBagConstraints gbc_textAreaBuscarMisListas = new GridBagConstraints();
		gbc_textAreaBuscarMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaBuscarMisListas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaBuscarMisListas.gridx = 2;
		gbc_textAreaBuscarMisListas.gridy = 1;
		panel.add(textAreaBuscarMisListas, gbc_textAreaBuscarMisListas);
	}
	private void agregaPanelMisListasBuscadas() {
		
		new JPanel();
		
		GridBagConstraints gbc_panelMisListasBuscadas = new GridBagConstraints();
		gbc_panelMisListasBuscadas.gridwidth = 2;
		gbc_panelMisListasBuscadas.gridheight = 4;
		gbc_panelMisListasBuscadas.insets = new Insets(0, 0, 5, 5);
		gbc_panelMisListasBuscadas.fill = GridBagConstraints.BOTH;
		gbc_panelMisListasBuscadas.gridx = 1;
		gbc_panelMisListasBuscadas.gridy = 3;
		
		panelBuscadas = new JScrollPane();
		panel.add(panelBuscadas, gbc_panelMisListasBuscadas);
	}
	private void agregaBotones() {
		
		agregaEventoBotonCrear();
	}
	
	private void mostrarVideosLista(String nombre) {
		
		List<Video> misListas;
		misListas = Controlador.getUnicaInstancia().getLista(nombre);
		
		videosPlaylist = JListRenderer.getInstancia().getListaR(misListas);

		videosPlaylist.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				if (me.getClickCount() == 1) {
					
					modeloEtiqeutasVideoSele.removeAllElements();
					
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Video video = (Video) item;
						
						videoSeleccionado = video;
						String[] d = Controlador.getUnicaInstancia().getEtiquetasVideo(video.getTitulo());
						
						for(int i = 0; i < d.length; i++) {
							
							modeloEtiqeutasVideoSele.addElement(d[i]);
						}
						
						etiquetasVideo.setModel(modeloEtiqeutasVideoSele);
					}
				}else if (me.getClickCount() == 2) {
					
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						Video video = (Video)item;
						videoSeleccionado = video;
						
						Controlador.getUnicaInstancia().addReciente(video);
						Controlador.getUnicaInstancia().reproducir(video.getTitulo(), video.getUrl());
						Controlador.getUnicaInstancia().agregarReproduccion(video);
					}
				}
			}
		});
		panelEtiquetas.setViewportView(etiquetasVideo);
		panelBuscadas.setViewportView(videosPlaylist);
		
	}
	private void videosMisListasBlanco() {
		
		String[] data = {""};
		JList<String> videosPlaylist = new JList<String>(data);
		panelBuscadas.setViewportView(videosPlaylist);
		
	}
	
	private void agregaEventoBotonCrear() {
		
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String texto = textFieldNuevaEtiqueta.getText();
				
				if(!texto.equals("")) {
					
					Etiqueta etiquetaCreada = Controlador.getUnicaInstancia().agregarEtiqueta(videoSeleccionado, texto);
					if(!modeloEtiqeutasVideoSele.contains(etiquetaCreada.getNombre()))
						modeloEtiqeutasVideoSele.addElement(etiquetaCreada.getNombre());
				}
			 }
			
		});
		panelEtiquetas.setViewportView(etiquetasVideo);
	}
	private void limpiarPanel() {
		
		JList<String> videosDeLista= new JList<String>();
		panelBuscadas.setViewportView(videosDeLista);
	}

}
