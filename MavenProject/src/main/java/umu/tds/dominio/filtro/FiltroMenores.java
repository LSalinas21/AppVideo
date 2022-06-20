package umu.tds.dominio.filtro;

import java.util.ArrayList;
import java.util.List;

import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.Video;

public class FiltroMenores implements FiltroVideo{

	private List<Etiqueta> bloqueadas;
	
	public FiltroMenores() {
		
		bloqueadas = new ArrayList<Etiqueta>();
		bloqueadas.add(new Etiqueta("Adulto"));
	}
	@Override
	public boolean esVideoOk(Video video) {
		
		for(Etiqueta eti: video.getEtiquetas())
			if(bloqueadas.contains(eti))
				return false;
		return true;
	}
	
	

}
