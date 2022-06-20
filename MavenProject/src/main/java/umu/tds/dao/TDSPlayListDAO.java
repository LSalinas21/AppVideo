package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Video;

public class TDSPlayListDAO implements PlayListDAO{
		
	private static final String NOMBRE = "nombre";

	private static final String VIDEOS = "videos";
	private static final String PLAYLIST = "playlist";
		
	private ServicioPersistencia servPersistencia;
		
	public TDSPlayListDAO() {
			
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

	}
		
	private PlayList entidadToPlayList(Entidad ePlayList) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		String videosIds = servPersistencia.recuperarPropiedadEntidad(ePlayList, VIDEOS);

		List<Video> videos = stringToVideo(videosIds);
			
			
		PlayList playList = new PlayList(nombre, videos);
		playList.setId(ePlayList.getId());
			
		return playList;
	}
		
	private Entidad playListToEntidad(PlayList playList) {
		
		Entidad ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);
		
		ePlayList.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, playList.getNombre()),
				new Propiedad(VIDEOS, videosToString(playList.getListaVideos())))));

		return ePlayList;
	}
		
	public PlayList create(PlayList playList) {
		
		Entidad ePlayList = this.playListToEntidad(playList);
		ePlayList = servPersistencia.registrarEntidad(ePlayList);
		playList.setId(ePlayList.getId());
		return playList;
	}

	public boolean delete(PlayList playList) {
		
		Entidad ePlayList;
		ePlayList = servPersistencia.recuperarEntidad(playList.getId());
		
		return servPersistencia.borrarEntidad(ePlayList);
	}

	public void update(PlayList playList) {

		Entidad ePlayList = servPersistencia.recuperarEntidad(playList.getId());
		
		if(ePlayList == null) {
			
			for (Propiedad prop : ePlayList.getPropiedades()) {
				if (prop.getNombre().equals(NOMBRE)) {
					prop.setValor(playList.getNombre());
				} else if (prop.getNombre().equals(VIDEOS)) {
					prop.setValor(videosToString(playList.getVideos()));
				}
				servPersistencia.modificarPropiedad(prop);
			}	
		}else {
			
			create(playList);
		}	
	}

	public PlayList get(int id) {

		Entidad ePlayList = servPersistencia.recuperarEntidad(id);
		if (ePlayList == null) {
			System.out.println("id de la playList Null");
			return null;
			
		}

		return entidadToPlayList(ePlayList);
	}

	public List<PlayList> getAll() {
		
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PLAYLIST);

		List<PlayList> playList = new LinkedList<PlayList>();
		for (Entidad ePlayList : entidades) {
			playList.add(get(ePlayList.getId()));
		}
		
		return playList;
	}

	public void deleteAll() {
		
		for(PlayList pl : getAll()) {
			delete(pl);
		}
			
	}
	private String videosToString(List<Video> lista) {
		
		String linea = "";
		if(lista.size() > 0) {
				
			for(Video v: lista) {
					
				linea += v.getId() + " ";
			}
				
			linea = linea.trim();
			
		}
		return linea;
		
	}
	private List<Video> stringToVideo(String linea) {
		
		List<Video> lista = new ArrayList<Video>();
		
		if(linea == null)
			return lista;
		if(linea.equals(""))
			return lista;
			
		String[] pls = linea.split(" ");
			
		for (String id: pls) {
						
			try {	
					TDSVideoDAO eVideo = new TDSVideoDAO();
					Video video = eVideo.get(Integer.parseInt(id));
					lista.add(video);
							
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}		
		}
		
		return lista;
	}

}
