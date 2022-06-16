package umu.tds.controlador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;
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
import umu.tds.gui.VideoApp;

public class Controlador implements VideosListener {
	
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;
	private static final int SIZE = 5;
	private CatalogoVideos catalogoDeVideos;
	private VideoApp reproductor;

	
	private Controlador() {
	
		reproductor = new VideoApp();

		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		CargadorVideos.getUnicaInstancia().addVideosListener(this);
		
	}
	public ImageIcon getIcono(String url) {
		
		return reproductor.getIcono(url);
	}
	public static Controlador getUnicaInstancia() {
		
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	public Usuario getUsuarioActual() {
		
		return usuarioActual;
	}
	public void reproducir(String titulo, String url) {
		
		reproductor.reproducir(titulo, url);
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

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); 
		usuarioDAO.delete(usuario);

		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}
	public boolean loginOutUsuario() {
		
		usuarioActual = null;
		return true;
	}
	///revisar
	public List<String> buscarMisListas(String lista){
		
		return usuarioActual.getPlayList(lista);
	}
	
	public List<String> getLista(String nombre){
		
		return usuarioActual.getListaVideos(nombre);
	}
	public String[] getMisListas(){
		
		return usuarioActual.getMisListas();
	}
	
	////fin revisar
	
	
	public void addReciente(Video v) {
		
		usuarioActual.setVideosReciente(v);
		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		usuarioDAO.update(usuarioActual);
	}
	public String[] getEtiquetasVideo(String video) {
		
		/* Hacer con java 8*/
		List<Etiqueta> et = new ArrayList<Etiqueta>();
		for(Video v: CatalogoVideos.getUnicaInstancia().getAllVideoss()) {
			
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
	public List<Video> buscarVideos(String busqueda){
		
		/* Hacer con java 8*/
		ArrayList<Video> lista = new ArrayList<Video>();
		for(Video v : CatalogoVideos.getUnicaInstancia().getAllVideoss()) {
	
			if(v.getTitulo().contains(busqueda))
				lista.add(v);
		}
		return lista;
	}
	public Video getVideo(String titulo) {
		
		return CatalogoVideos.getUnicaInstancia().buscarVideo(titulo);
	}
	public List<String> getEtiquetas(){
		
		List<String> lista = CatalogoVideos.getUnicaInstancia().getAllVideoss().stream()
				.map(video -> video.getEtiquetas())
				.flatMap(et -> et.stream())
				.map(e -> e.getNombre())
				.distinct()
				.collect(Collectors.toList());
		
		
		return lista;
		
	}
	public List<String> buscarVideosPorEtiquetas(List<String> seleccionadas){
		
		List<String> lista = new ArrayList<String>();
		
		CatalogoVideos.getUnicaInstancia().getAllVideoss().stream()
				.forEach(vi -> {
							for(String etiS : seleccionadas)
								for(Etiqueta eti: vi.getEtiquetas())
									if(eti.getNombre().equals(etiS) && !lista.contains(vi.getTitulo()))
										lista.add(vi.getTitulo());
										
						});

				return lista;
			
	}
	public Etiqueta agregarEtiqueta(String video,String nomEtiqueta) {
		
		return CatalogoVideos.getUnicaInstancia().setNuevaEtiqueta(video, nomEtiqueta);
	}
	
	public void crearPlayList(String nombreLista, List<String> videos) {
		
		List<Video> listaVideos = CatalogoVideos.getUnicaInstancia().getListaDeVideos(videos);
		usuarioActual.creaListaRep(nombreLista, listaVideos);
	}
	public void borrarPlayList(String nombreLista) {
		
		usuarioActual.borrarPlayList(nombreLista);
	}
	public void agregarReproduccion(Video video) {
		
		CatalogoVideos.getUnicaInstancia().agregarReproduccion(video);
	}
	
	public boolean cargarVideosDesdeFichero(String xmlFile) {
		
		return CargadorVideos.getUnicaInstancia().setArchivoVideos(xmlFile);
	}
	public void enteradoCambioVideos(EventObject event) {
		if (event instanceof VideosEvent) {
			
			VideoDAO videoDAO = factoria.getVideoDAO();
			
			for (Video video : adaptarVideos(((VideosEvent) event).getVideos())) {
				
				CatalogoVideos.getUnicaInstancia().addVideo(video);
				
				videoDAO.create(video);
			}
		}
	}
	
	public static List<Video> adaptarVideos(Videos videos) {
		
		List<Video> lista = new LinkedList<Video>();
		for (umu.tds.componente.Video video : videos.getVideo())
			lista.add(new Video(video));
		return lista;
	}
	public void borrarVideo(Video vid) {
		
		factoria.getVideoDAO().delete(vid);
	}
}
