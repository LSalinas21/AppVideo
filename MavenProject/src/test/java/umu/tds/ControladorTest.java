package umu.tds;

import static org.junit.Assert.*;

import java.io.File;
import java.util.*;
import javax.xml.bind.JAXBException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import umu.tds.controlador.Controlador;
import umu.tds.dao.UsuarioDAO;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public class ControladorTest {

	static final String NICK = "nick1";
	static final String PASS = "1234";
	static final String PLAYLIST = "ListaVideosTest";
	static String RUTA = "src/test/java/xml/videosTest.xml";
	
	static List<Video> videos = new ArrayList<Video>();
	static Controlador controlador;
	static UsuarioDAO usuarioDAO;
	static Usuario usuario;

	@BeforeClass
	public static void setUp() throws JAXBException {

		controlador = Controlador.getUnicaInstancia();
		
		if (controlador.esUsuarioRegistrado(NICK)) {
			assertTrue(controlador.loginUsuario(NICK, PASS));
			controlador.borrarUsuario(usuario);
		}
		
		try {
			File vids = new File(RUTA);
			assertTrue("Permisos de lectura", vids.canRead());
			RUTA = vids.getAbsolutePath();
		} catch (NullPointerException e) {
			assertTrue("Carga de videos para el test", false);
		}
		
		videos.add(new Video("url1","VID1", 0));
		videos.add(new Video("url2","VID2", 0));
		videos.add(new Video("url3","VID3", 0));
		videos.add(new Video("url4","VID4" ,0));
		videos.add(new Video("url5","VID5", 0));
		videos.add(new Video("url6","VID6", 0));

		testInicializacion();
	}
	
	//@Test
	public static void testInicializacion() throws JAXBException {	

		assertTrue("Registro", controlador.registrarUsuario("nom1", "apell1", "user1@mail.test", NICK, PASS, "29/09/1994"));
		
		assertTrue("Usuario registrado", controlador.esUsuarioRegistrado(NICK));
		
		assertTrue("Inicio sesion", controlador.loginUsuario(NICK, PASS));
		
		usuario = controlador.getUsuarioActual();
		assertNotNull(usuario);
		
		assertTrue("Cargando fichero " + RUTA, controlador.cargarVideosDesdeFichero(RUTA));
	}
	
	@Test
	public void testPlaylists() {

		List<Video> controladorVideo = controlador.buscarVideos("", new ArrayList<String>());
		List<Video> listaVideos = new ArrayList<Video>();
		Video aux;
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(0).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(1).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(2).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(3).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(4).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		aux = controladorVideo.stream().filter(v -> v.getUrl().equals(videos.get(5).getUrl())).findAny().get();
		assertNotNull(aux);
		listaVideos.add(aux);
		
		controlador.crearPlayList(PLAYLIST, listaVideos);
		
		assertTrue(controlador.getLista(PLAYLIST) != null);
		
		List<Video> videosRecuperados = controlador.getLista(PLAYLIST);
		for (Video vid : listaVideos)
			assertTrue(videosRecuperados.contains(vid));
		
		listaVideos.remove(aux);
		controlador.crearPlayList(PLAYLIST, listaVideos);
		

		videosRecuperados = controlador.getLista(PLAYLIST);
		assertTrue(controlador.getPlayList().size() == 1);
		assertFalse(videosRecuperados.contains(aux));
		for (Video vid : listaVideos)
			assertTrue(videosRecuperados.contains(vid));
		
		controlador.borrarPlayList(PLAYLIST);
		assertTrue(controlador.getPlayList().size() == 0);
		
		for(Video v: listaVideos)
			Controlador.getUnicaInstancia().borrarVideo(v);
	}
	
	@AfterClass
	public static void testBorrado() {
		if (usuario != null) {
			
			controlador.borrarUsuario(usuario);
			assertFalse(controlador.esUsuarioRegistrado(NICK));
		}
	}

}
