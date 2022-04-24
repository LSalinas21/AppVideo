package umu.tds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Usuario<playListReciente> {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String nick;
	private String password;
	private String fechaNacimiento;
	private List<PlayList> misListas;
	private List<Video> recientes;

	public Usuario(String nombre, String apellido, String email, String nick, String password, String fechaNacimiento) {

		this.nombre = nombre;
		this.apellidos = apellido;
		this.email = email;
		this.nick = nick;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		misListas = new ArrayList<PlayList>();
		recientes = new LinkedList<Video>();

	}

	public List<Video> getVideosRecientes() {
		return recientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getMisListas() {
		
		String[] playList = new String[misListas.size()+1];
		playList[0] = "---Selecciona---";
		int i = 1;
		for(PlayList pl: misListas) {
			
			playList[i] = pl.getNombre();
			i++;
		}
		return playList;
	}
	public ArrayList<String> getPlayList(String nombre) {
		
		ArrayList<String> lista = new ArrayList<String>();
		for(PlayList l: misListas) {

			if(l.getNombre().contains(nombre))
				lista.add(l.getNombre());
		}
		return lista;
	}
	public ArrayList<String> getListaVideos(String nombre) {
		
		ArrayList<String> lista = new ArrayList<String>();
		for(PlayList l: misListas) {

			if(l.getNombre().contains(nombre))
				lista = l.getListaVideos();
		}
		return lista;
	}

	public void creaListaRep(String nombreLista, ArrayList<Video> listaVideos) {
		
		PlayList lista = new PlayList(nombreLista, listaVideos);
		misListas.add(lista);
	}

	public void setVideosRecientes(List listaRecientes) {
		this.recientes = listaRecientes;
	}

}
