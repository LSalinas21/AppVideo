package umu.tds.dominio;

import java.util.HashMap;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public class CatalogoVideos {
	
	private static CatalogoVideos unicaInstancia;
	private FactoriaDAO factoria;
	private HashMap<String, Video> videosExistentes;
	
	private CatalogoVideos (){
		
		videosExistentes = new HashMap<String, Video>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Video> listaVideos = factoria.getVideoDAO().getAll();
			for (Video video : listaVideos) {
				
				videosExistentes.put(video.getUrl(), video);

			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
		
	}
	
	
	public static CatalogoVideos getUnicaInstancia() {
		
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoVideos();
		
		return unicaInstancia;
	}

}
