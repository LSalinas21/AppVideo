package umu.tds.dominio;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	
	private String nombre;
	private int id;
	private List<Video> videos = new ArrayList<Video>();
	
	public PlayList(String nombre) {
		
		this.nombre = nombre;
	}
	public PlayList(String nombre, List<Video> videos) {
		
		this.nombre = nombre;
		this.videos = videos;
	}
	
	public String getNombre() {
		
		return nombre;
	}
	public List<Video> getVideos(){
		
		return videos;
	}
	public int getId() {
		
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	public int addVideo(Video video) {
		assert(video == null): "El video añadido a la lista de videos es un objeto null";
		videos.add(video);
		return video.getId();
	}
	
	public int getNumVideos()
	{
		return videos.size();
	}
	public List<Video> getListaVideos(){
		
		return videos;
	}

}
