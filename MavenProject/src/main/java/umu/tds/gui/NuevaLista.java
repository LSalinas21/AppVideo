package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class NuevaLista {
	
	private JPanel panel,panelIzquierdo,panelDerecho;
	private JSplitPane splitPane;
	private JLabel labelNombre,labelBuscarTitulo;
	private JTextField textFieldNombre,textFieldBuscarTitulo;
	private JButton botonBuscarLista,botonBorrarLista,botonAñadir,botonAceptar,botonQuitar,botonNuevaBusqueda,botonBuscarVideo;
	private JScrollPane panelListaVideos,panelVideosBuscados;

	
	public NuevaLista() {
		
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
		
		botonBorrarLista = new JButton("Borrar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 3;
		panelIzquierdo.add(botonBorrarLista, gbc_btnNewButton_5);
		
		botonAñadir = new JButton("Añadir");
		botonAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 9;
		panelIzquierdo.add(botonAñadir, gbc_btnNewButton_3);
		
		botonQuitar = new JButton("Quitar");
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitar.gridx = 2;
		gbc_btnQuitar.gridy = 9;
		panelIzquierdo.add(botonQuitar, gbc_btnQuitar);
		
		botonAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 10;
		panelIzquierdo.add(botonAceptar, gbc_btnNewButton_4);
		
		botonNuevaBusqueda = new JButton("Nueva Busqueda");
		botonNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 1;
		gbc_btnNewButton_7.gridy = 2;
		panelDerecho.add(botonNuevaBusqueda, gbc_btnNewButton_7);
		
		botonBuscarVideo = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 4;
		gbc_btnNewButton_6.gridy = 2;
		panelDerecho.add(botonBuscarVideo, gbc_btnNewButton_6);
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

}
