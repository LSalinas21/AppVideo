package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public class CatalogoVideos {
	
	private static CatalogoVideos unicaInstancia;
	private FactoriaDAO factoria;
	private HashMap<Integer, Video> videosExistentes;
	
	private CatalogoVideos (){
		
		videosExistentes = new HashMap<Integer, Video>();
		/*
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Video> listaVideos = factoria.getVideoDAO().getAll();
			for (Video video : listaVideos) {
				
				videosExistentes.put(video.getUrl(), video);

			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}*/
		
	}
	
	
	public static CatalogoVideos getUnicaInstancia() {
		
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoVideos();
		
		return unicaInstancia;
	}
	public List<Video> getAllVideoss() {
		
		return new LinkedList<Video>(videosExistentes.values());
	}
	public void addVideo(Video video) {
		
		videosExistentes.put(video.getId(), video);
	}
	

}
