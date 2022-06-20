package umu.tds.dominio.filtro;

import java.util.List;

import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public class FiltroMisListas implements FiltroVideo{
	
	private Usuario actual;
	public static final String tipo = "MisListas";
	
	public FiltroMisListas(Usuario user) {
		
		actual = user;
	}
	@Override
	public boolean esVideoOk(Video video) {
		List<PlayList> pls = actual.getPlayList();
		
		for(PlayList pl: pls) {
			for(Video v: pl.getListaVideos()) {
				if(v.getId() == video.getId())
					return false;
			}
		}
		return true;
	}


	
}
