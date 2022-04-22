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
	private Map<String, ArrayList<Video>> misListas;
	private List<Video> recientes;

	public Usuario(String nombre, String apellido, String email, String nick, String password, String fechaNacimiento) {

		this.nombre = nombre;
		this.apellidos = apellido;
		this.email = email;
		this.nick = nick;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		misListas = new HashMap<String, ArrayList<Video>>();
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

	public ArrayList<Video> getLista(String nombreLista) {

		if (misListas.containsKey(nombreLista)) {

			return misListas.get(nombreLista);
		} else {

			return null;
		}
	}

	public void creaListaRep(String nombreLista, ArrayList<Video> listaVideos) {

		misListas.put(nombreLista, listaVideos);
	}

	public void setVideosRecientes(List listaRecientes) {
		this.recientes = listaRecientes;
	}

}
