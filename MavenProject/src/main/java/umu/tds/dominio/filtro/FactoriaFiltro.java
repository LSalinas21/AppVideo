package umu.tds.dominio.filtro;

import umu.tds.dominio.Usuario;

public class FactoriaFiltro {

	private static FactoriaFiltro unicaInstancia = null;
	public static final String FILTRO_TDS = "umu.tds.dominio.Filtro";
	
	public static FactoriaFiltro getInstancia() {
		
		if (unicaInstancia == null)
			unicaInstancia = new FactoriaFiltro();
		return unicaInstancia;
	}
	private FactoriaFiltro() {
	}
	public FiltroVideo crearFiltro() {
		return new NoFiltro();
	}
	public FiltroVideo crearFiltro(String tipo, Usuario user) {
	
		if(tipo.equals("MisListas")) {
			
			return new FiltroMisListas(user);
		}else if(user.esMenor() && tipo.equals("Menores")) {

			return new FiltroMenores();
		}else {
			
			return new NoFiltro();
		}
		
	}
}
