package umu.tds.dominio.filtro;

import umu.tds.dominio.Video;

public class NoFiltro implements FiltroVideo{

	@Override
	public boolean esVideoOk(Video video) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
