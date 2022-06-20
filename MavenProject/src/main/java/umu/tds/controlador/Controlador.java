package umu.tds.controlador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.xml.bind.JAXBException;

import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.PlayListDAO;
import umu.tds.dao.UsuarioDAO;
import umu.tds.dao.VideoDAO;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.CatalogoVideos;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;
import umu.tds.dominio.filtro.FiltroVideo;
import umu.tds.herramientas.GeneradorPDF;
import umu.tds.herramientas.VideoApp;

public class Controlador implements VideosListener {
	
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;
	private static final int SIZE = 5;
	private CatalogoVideos catalogoDeVideos;
	private CatalogoUsuarios catalogoDeUsuarios;
	private VideoApp reproductor;
	private UsuarioDAO userBD;
	private VideoDAO videoBD;
	private PlayListDAO plBD;
	private CargadorVideos cargador;

	
	private Controlador() {
	
		reproductor = new VideoApp();
		catalogoDeVideos = CatalogoVideos.getUnicaInstancia();
		catalogoDeUsuarios = CatalogoUsuarios.getUnicaInstancia();
		cargador = CargadorVideos.getUnicaInstancia();

		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		userBD = factoria.getUsuarioDAO();
		videoBD = factoria.getVideoDAO();
		plBD = factoria.getPlayListDAO();
		cargador.addVideosListener(this);
		
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
		
		return catalogoDeUsuarios.getUsuario(login) != null;
	}
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {
		
		if (esUsuarioRegistrado(login))
			return false;
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

		userBD.create(usuario);
		
		catalogoDeUsuarios.addUsuario(usuario);
		return true;
	}
	public boolean loginUsuario(String nombre, String password) {
		
		Usuario usuario = catalogoDeUsuarios.getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean borrarUsuario(Usuario usuario) {
		
		if (!esUsuarioRegistrado(usuario.getNick()))
			return false;

		userBD.delete(usuario);

		catalogoDeUsuarios.removeUsuario(usuario);
		return true;
	}
	public boolean loginOutUsuario() {
		
		//borrarUsuario(usuarioActual);
		usuarioActual = null;
		return true;
	}
	///revisar

	public List<Video> getLista(String nombre){
		
		return usuarioActual.getListaVideos(nombre);
	}
	public String[] getMisListas(){
		
		return usuarioActual.getMisListas();
	}
	public List<PlayList> getPlayList(){
		
		return usuarioActual.getPlayList();
	}
	public void addReciente(Video v) {
		
		usuarioActual.setVideosReciente(v);
		userBD.update(usuarioActual);
	}
	public List<Video> getRecientes(){
		
		List<Video> lista = new LinkedList<Video>(usuarioActual.getVideosRecientes());
		Collections.reverse(lista);	
		
		return lista;
	}
	public String[] getEtiquetasVideo(String video) {
		
		List<String> et = catalogoDeVideos.getAllVideoss().stream()
				.filter(v -> v.getTitulo().equals(video))
				.map(vid -> vid.getEtiquetas())
				.flatMap(eti -> eti.stream())
				.map(e -> e.getNombre())
				.distinct()
				.collect(Collectors.toList());
				
		return et.toArray(new String[et.size()]);
	}
	public List<Video> buscarVideos(String titulo, List<String> seleccionadas){
		
		if(titulo.equals("") && seleccionadas.size() > 0) {
			
			List<Video> lista = catalogoDeVideos.getAllVideoss().stream()
					.filter(video -> video.getEtiquetas().stream()
							.map(eti -> eti.getNombre())
							.anyMatch(e -> seleccionadas.contains(e)))
					.collect(Collectors.toList());
			return lista;
			
		}else if(!titulo.equals("") && seleccionadas.size() > 0){
			
			List<Video> lista = catalogoDeVideos.getAllVideoss().stream()
					.filter(video -> video.getEtiquetas().stream()
							.map(eti -> eti.getNombre())
							.anyMatch(e -> seleccionadas.contains(e)))
					.filter(vid -> vid.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
					.collect(Collectors.toList());
		
			return lista;
		}else {
			
			List<Video> lista = catalogoDeVideos.getAllVideoss().stream()
					.filter(vid -> vid.getTitulo().toLowerCase().contains(titulo.toLowerCase()) && usuarioActual.getFiltro().esVideoOk(vid))
					.collect(Collectors.toList());
	
			return lista;
		}

	}
	public Video getVideo(String titulo) {
		
		return catalogoDeVideos.buscarVideo(titulo);
	}
	public List<String> getEtiquetas(){
		
		List<String> lista = catalogoDeVideos.getAllVideoss().stream()
				.map(video -> video.getEtiquetas())
				.flatMap(et -> et.stream())
				.map(e -> e.getNombre())
				.distinct()
				.collect(Collectors.toList());
		
		
		return lista;
		
	}
	public Etiqueta agregarEtiqueta(Video video,String nomEtiqueta) {
		
		Etiqueta nueva = catalogoDeVideos.setNuevaEtiqueta(video, nomEtiqueta);
		videoBD.update(video);
		return nueva;
	}
	
	public void crearPlayList(String nombreLista, List<Video> videos) {
		
		usuarioActual.actualizarLista(plBD.create(usuarioActual.creaListaRep(nombreLista, videos)));
		userBD.update(usuarioActual);
	}
	public void borrarPlayList(String nombreLista) {
		
		plBD.delete(usuarioActual.borrarPlayList(nombreLista));
		userBD.update(usuarioActual);
	}
	public void agregarReproduccion(Video video) {
		
		catalogoDeVideos.agregarReproduccion(video);
		videoBD.update(video);
	}
	
	public boolean cargarVideosDesdeFichero(String xmlFile) throws JAXBException {

		return cargador.setArchivoVideos(xmlFile);
	}
	public void enteradoCambioVideos(EventObject event) {
		
		if (event instanceof VideosEvent) {
			
			for (Video video : adaptarVideos(((VideosEvent) event).getVideos())) {
				
				catalogoDeVideos.addVideo(videoBD.create(video));
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
		
		videoBD.delete(vid);
		catalogoDeVideos.borrarVideo(vid);
	}
	public String[] getFiltros() {
		
		return FiltroVideo.tipos;
	}
	public void setFiltroUsuario(String tipo) {
		
		usuarioActual.setFiltro(tipo);
		userBD.update(usuarioActual);
	}
	public void setPremiun() {
		
		usuarioActual.setPremiun("yes");
		userBD.update(usuarioActual);
	}
	public List<Video> getTopTen(){
		
		List<Video> lista = catalogoDeVideos.getAllVideoss().stream()
							.sorted(Comparator.comparing(Video::getNumRepro).reversed())
							.limit(10)
							.collect(Collectors.toList());
		
		return lista;
	}
	public String getFiltroActivo() {
		if(usuarioActual != null)
			return usuarioActual.getFiltroToString();
		else
			return "NoFiltro";
	}
	public void generarPDF(JFrame frame) {
		
		GeneradorPDF.generarPDF(frame);
	}
}
