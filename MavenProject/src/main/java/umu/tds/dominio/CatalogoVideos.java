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
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Video> listaVideos = factoria.getVideoDAO().getAll();
			for (Video video : listaVideos) {
				
				videosExistentes.put(video.getId(), video);

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
	public List<Video> getAllVideoss() {
		
		return new LinkedList<Video>(videosExistentes.values());
	}
	public void addVideo(Video video) {
		
		videosExistentes.put(video.getId(), video);
	}
	public Video buscarVideo(String titulo) {
		
		for(Video v : videosExistentes.values()) {
			
			if(v.getTitulo().equals(titulo))
				return v;
			
		}
		return null;
	}
	public Etiqueta setNuevaEtiqueta(Video video, String nomEtiqueta) {
		
		Video v = videosExistentes.get(video.getId());
		
		Etiqueta nueva = v.agregarEtiqueta(nomEtiqueta);
		
		if(nueva != null)
			videosExistentes.replace(v.getId(), v);
		
		return nueva;

	}
	public List<Video> getListaDeVideos(List<String> videos){
		
		List<Video> lista = new LinkedList<Video>();
		
		for(String titu: videos) {
			
			for(Video v : videosExistentes.values()) {
				
				if(v.getTitulo().equals(titu)) {
					lista.add(v);
					break;
				}
			}
		}
		return lista;
	}
	public Video agregarReproduccion(Video video) {
		
		Video vidMod = videosExistentes.get(video.getId());
		vidMod.setNumRepro(vidMod.getNumRepro() + 1);
		
		videosExistentes.replace(vidMod.getId(), vidMod);
		
		return vidMod;
		
	}
	public void borrarVideo(Video video) {
		
		videosExistentes.remove(video.getId());
	}
	public boolean videoExistente(Video video) {
		
		for(Video v: videosExistentes.values()) {
			
			if(v.getUrl().equals(video.getUrl()))
				return true;
		}
		return false;
	}
	

}
