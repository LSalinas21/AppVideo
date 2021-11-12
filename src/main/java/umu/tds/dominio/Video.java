package umu.tds.dominio;

public class Video {
	
	private String url;
	private String titulo;
	private int numRepro;
	
	public Video(String url, String titulo, int numRepro) {
		
		this.url = url;
		this.titulo = titulo;
		this.numRepro = numRepro;
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
	
	

}
