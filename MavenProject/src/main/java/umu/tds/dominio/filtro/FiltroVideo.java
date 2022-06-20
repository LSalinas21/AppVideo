package umu.tds.dominio.filtro;

import umu.tds.dominio.Video;

public interface FiltroVideo {
	
	public static final String[] tipos = {"Menores","MisListas","NoFiltro"};
	
	public boolean esVideoOk(Video video);
	
	public static String esTipoFiltro(FiltroVideo filtro) {
		
		String[] tk = filtro.getClass().getName().split("\\.");
		
		for(int i = 0; i< tipos.length; i++) {
			
			if(tk[tk.length-1].equals("Filtro"+tipos[i])) {
				
				return tipos[i];
			}
		}
		return "NoFiltro";
		
	}
	
}
