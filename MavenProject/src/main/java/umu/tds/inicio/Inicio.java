package umu.tds.inicio;

import java.awt.EventQueue;

import umu.tds.gui.Principal;
import umu.tds.controlador.Controlador;
import umu.tds.gui.Login;

public class Inicio {
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.mostrarVentana();
					window.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
