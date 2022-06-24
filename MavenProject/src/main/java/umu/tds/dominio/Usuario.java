package umu.tds.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import umu.tds.dominio.filtro.FactoriaFiltro;
import umu.tds.dominio.filtro.FiltroVideo;

public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String nick;
	private String password;
	private String fechaNacimiento;
	private List<PlayList> misListas;
	private Queue<Video> recientes;
	private final int MAX_SIZE = 5;
	private FiltroVideo filtro;
	private boolean premiun;

	public Usuario(String nombre, String apellido, String email, String nick, String password, String fechaNacimiento) {

		this.nombre = nombre;
		this.apellidos = apellido;
		this.email = email;
		this.nick = nick;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		misListas = new ArrayList<PlayList>();
		recientes = new ArrayDeque<Video>();
		premiun = false;

		filtro = FactoriaFiltro.getInstancia().crearFiltro();

	}

	public Queue<Video> getVideosRecientes() {
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
	public String filtroToString() {
		
		return filtro.getClass().getName();
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

	public List<PlayList> getAllPlayList() {
		
		return misListas;
	}
	public List<Video> getListaVideos(String nombre) {

		for(PlayList l: misListas) {

			if(l.getNombre().equals(nombre)) {
				
				return l.getListaVideos();
			}
		}
		return null;
	}

	public PlayList creaListaRep(String nombreLista, List<Video> listaVideos) {
		
		for(PlayList pl: misListas) {
			
			if(pl.getNombre().equals(nombreLista)) {
				
				misListas.remove(pl);
				pl.setVideos(listaVideos);
				return pl;
			}
		}
			
		PlayList lista = new PlayList(nombreLista, listaVideos);
		return lista;	
	}
	public void actualizarLista(PlayList pl) {
		
		misListas.add(pl);
	}
	public void setVideosReciente(Video video) {
		
		Video aux = null;
		for(Video vi: recientes)
			if(vi.getUrl().equals(video.getUrl()))
				aux = vi;
				
		if (aux != null) {
			
			recientes.remove(aux);
			
		}else if(recientes.size() == MAX_SIZE) {
			
			recientes.poll();
		}
		this.recientes.add(video);
	}
	public PlayList borrarPlayList(String nombre) {
		
		for(PlayList pl: misListas) {
			
			if(pl.getNombre().equals(nombre)) {
				
				misListas.remove(pl);
				return pl;
			}
		}
		return null;
	}
	public void setRecientes(Queue<Video> rec) {
		
		recientes = rec;
	}
	public void setPlayList(List<PlayList> ml) {
		
		misListas = ml;
	}
	public void setFiltro(String filtro) {
		this.filtro = FactoriaFiltro.getInstancia().crearFiltro(filtro, this);
	}
	public FiltroVideo getFiltro() {
		return filtro;
	}
	public boolean esMenor() {
		
		int edad = Period.between(LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.now()).getYears();
		if (edad < 18)
			return true;
		else
			return false;
	}
	public void setPremiun(String op) {
		
		if(op.equals("yes"))
			premiun = true;
		else
			premiun = false;
	}
	public boolean getPremiun() {
		
		return premiun;
	}
	public String getPremiunToString() {
		
		if(premiun)
			return "yes";
		else
			return "no";
	}
	public String getFiltroToString() {
		
		return FiltroVideo.esTipoFiltro(filtro);
	}

}
