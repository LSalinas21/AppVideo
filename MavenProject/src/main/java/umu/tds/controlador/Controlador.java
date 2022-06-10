package umu.tds.controlador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.UsuarioDAO;
import umu.tds.dao.VideoDAO;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.CatalogoVideos;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public class Controlador {
	
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;
	private static final int SIZE = 5;
	Queue<Video> recientes = new ArrayDeque<Video>(SIZE);
	private ArrayList<Video> lis;
	private CatalogoVideos catalogoDeVideos;
	
	private Controlador() {
		// Pruebas
		Etiqueta e1 = new Etiqueta("etiqueta1");
		Etiqueta e2 = new Etiqueta("etiqueta2");
		Etiqueta e3 = new Etiqueta("etiqueta3");
		Etiqueta e4 = new Etiqueta("etiqueta4");
		Etiqueta e5 = new Etiqueta("etiqueta5");
		Video v1 = new Video("url1", "titulo1", 1);
		v1.setId(1);
		Video v2 = new Video("url2", "titulo2", 2);
		v2.setId(2);
		Video v3 = new Video("url3", "titulo3", 3);
		v3.setId(3);
		Video v4 = new Video("url4", "titulo4", 4);
		Video v5 = new Video("url5", "titulo5", 5);
		v1.agregarEtiqueta(e1);
		v1.agregarEtiqueta(e2);
		v2.agregarEtiqueta(e2);
		v2.agregarEtiqueta(e3);
		v3.agregarEtiqueta(e3);
		v3.agregarEtiqueta(e4);
		v4.agregarEtiqueta(e4);
		v4.agregarEtiqueta(e5);
		v5.agregarEtiqueta(e1);
		v5.agregarEtiqueta(e2);
		v5.agregarEtiqueta(e3);
		v5.agregarEtiqueta(e4);
		v5.agregarEtiqueta(e5);
		Usuario a = new Usuario("aaa","aaa","aaa","aaa","aaa","aaa");
		lis = new ArrayList<Video>();
		lis.add(v1);
		lis.add(v2);
		lis.add(v3);
		lis.add(v4);
		lis.add(v5);
		a.creaListaRep("primera", lis);
		a.creaListaRep("segunada", lis);
		a.creaListaRep("tercera", lis);
		a.creaListaRep("cuarta", lis);
		//Fin pruebas
		
		catalogoDeVideos = CatalogoVideos.getUnicaInstancia();
		
		catalogoDeVideos.addVideo(v1);
		catalogoDeVideos.addVideo(v2);
		catalogoDeVideos.addVideo(v3);
		
		usuarioActual = a;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		VideoDAO videoDAO = factoria.getVideoDAO();
		videoDAO.create(v1);
		
	}
	
	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(login) != null;
	}
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {
		
		if (esUsuarioRegistrado(login))
			return false;
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		
		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.create(usuario);
		
		CatalogoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}
	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getNick()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}
	public boolean loginOutUsuario() {
		
		usuarioActual = null;
		return true;
	}
	public List<String> buscarMisListas(String lista){
		
		return usuarioActual.getPlayList(lista);
	}
	
	public List<String> getLista(String nombre){
		
		return usuarioActual.getListaVideos(nombre);
	}
	public String[] getMisListas(){
		
		return usuarioActual.getMisListas();
	}
	
	public void addReciente(Video v) {
		List listaRecientes = new LinkedList<Video>(recientes);
		listaRecientes = usuarioActual.getVideosRecientes();
		
		if (listaRecientes.contains(v)) {
			listaRecientes.remove(v);
			listaRecientes.add(0, v);
		}else {
			if (listaRecientes.size()<5) {
				listaRecientes.add(0,v);
			}else {
				listaRecientes.remove(SIZE-1);
				listaRecientes.add(0, v);
			}
		}
		
		usuarioActual.setVideosRecientes(listaRecientes);
		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.update(usuarioActual);
	}
	public String[] getEtiquetasVideo(String video) {
		
		/* Aqui hay que buscar el video en el catalogo de videos*/
		ArrayList<Etiqueta> et = new ArrayList<Etiqueta>();
		for(Video v: lis) {
			
			if(v.getTitulo().equals(video)) {
				et = v.getEtiquetas();
				break;
			}
		}
		String[] etiquetas = new String[et.size()];
		for(int i=0; i < et.size(); i++) {
			
			etiquetas[i] = et.get(i).getNombre();
		}
		return etiquetas;
	}
	public List<String> buscarVideos(String busqueda){
		
		ArrayList<String> lista = new ArrayList<String>();
		for(Video v : catalogoDeVideos.getAllVideoss()) {
	
			if(v.getTitulo().contains(busqueda))
				lista.add(v.getTitulo());
		}
		return lista;
	}

}
