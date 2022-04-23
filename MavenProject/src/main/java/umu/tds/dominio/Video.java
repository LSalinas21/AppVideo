package umu.tds.dominio;

import java.util.ArrayList;

public class Video {
	
	private String url;
	private String titulo;
	private int numRepro;
	private int id;
	private ArrayList<Etiqueta> listaEtiquetas;
	
	public Video(String url, String titulo, int numRepro) {
		
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
		listaEtiquetas = new ArrayList<Etiqueta>();
	}
	
	public Video(String url, String titulo, int numRepro, ArrayList<Etiqueta> etiquetas) {
		
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
		listaEtiquetas = etiquetas;
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
	public ArrayList<Etiqueta> getEtiquetas(){
		
		return listaEtiquetas;
	}
	
	

}
