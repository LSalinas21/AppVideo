package umu.tds.dominio;

import java.util.ArrayList;
import java.util.List;

public class Video {
	
	private String url;
	private String titulo;
	private int numRepro;
	private int id;
	private List<Etiqueta> listaEtiquetas;
	
	public Video(String url, String titulo, int numRepro) {
		
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
		listaEtiquetas = new ArrayList<Etiqueta>();
	}
	
	public Video(String url, String titulo, int numRepro, List<Etiqueta> etiquetas) {
		
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
		listaEtiquetas = etiquetas;
	}
	public Video(umu.tds.componente.Video video) {
		
		this(video.getURL(),video.getTitulo(), 0);

		for(String eti: video.getEtiqueta())
			listaEtiquetas.add(new Etiqueta(eti));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumRepro() {
		return numRepro;
	}

	public void setNumRepro(int numRepro) {
		this.numRepro = numRepro;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	public void agregarEtiqueta(Etiqueta etiqueta) {
		
		listaEtiquetas.add(etiqueta);
	}
	public List<Etiqueta> getEtiquetas(){
		
		return listaEtiquetas;
	}
	
	

}
