package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Recientes {
	
	private JPanel panel,panelRecientes;
	private JLabel labelMasRecientes;
	private JScrollPane scrollPaneMasRecientes;
	
	public Recientes() {
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		agregaLabels();
		agregaPanelRecientes();
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

}
