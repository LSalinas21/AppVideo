package umu.tds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public class TDSVideoDAO implements VideoDAO{
	
	private static final String VIDEO = "Video";

	private static final String URL = "url";
	private static final String TITULO = "titulo";
	private static final String NUM_REPRO = "numRepro";
	
	private ServicioPersistencia servPersistencia;
	
	public TDSVideoDAO() {
		
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

	}
	
	private Video entidadToVideo(Entidad eVideo) {

		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, URL);
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, TITULO);
		String numRepro = servPersistencia.recuperarPropiedadEntidad(eVideo, NUM_REPRO);
		
		Video video = new Video(url, titulo, Integer.parseInt(numRepro));
		
		return video;
	}
	
	private Entidad videoToEntidad(Video video) {
		
		Entidad eVideo = new Entidad();
		eVideo.setNombre(VIDEO);

		eVideo.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(URL, video.getUrl()),
				new Propiedad(TITULO, video.getTitulo()), new Propiedad(NUM_REPRO, String.valueOf(video.getNumRepro())))));
		return eVideo;
	}
	
	public void create(Video video) {
		
		Entidad eVideo = this.videoToEntidad(video);
		eVideo = servPersistencia.registrarEntidad(eVideo);
		
	}
	
	public boolean delete(Video video) {
		
		Entidad eVideo;
		eVideo = servPersistencia.recuperarEntidad(video.getId());

		return servPersistencia.borrarEntidad(eVideo);
	}
	
	public Video get(int id) {
		Entidad eVideo = servPersistencia.recuperarEntidad(id);

		return entidadToVideo(eVideo);
	}

	public List<Video> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(VIDEO);

		List<Video> videos = new LinkedList<Video>();
		for (Entidad eVideo : entidades) {
			videos.add(get(eVideo.getId()));
		}

		return videos;
	}

	public void update(Video existente) {
		// TODO Auto-generated method stub
		Entidad eVideo = servPersistencia.recuperarEntidad(existente.getId());

		for (Propiedad prop : eVideo.getPropiedades()) {
			if (prop.getNombre().equals(URL)) {
				prop.setValor(existente.getUrl());
			} else if (prop.getNombre().equals(TITULO)) {
				prop.setValor(existente.getTitulo());
			} else if (prop.getNombre().equals(NUM_REPRO)) {
				prop.setValor(String.valueOf(existente.getNumRepro()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
		
	}


}
