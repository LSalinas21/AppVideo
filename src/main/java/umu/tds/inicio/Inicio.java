package umu.tds.inicio;

import java.awt.EventQueue;

import umu.tds.gui.Login;

public class Inicio {
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
