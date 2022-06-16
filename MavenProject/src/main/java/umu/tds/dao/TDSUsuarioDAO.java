package umu.tds.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Etiqueta;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String NICK = "nick";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String RECIENTE = "videosRecientes";
	private static final String PLAYLISTS = "playlist";

	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, NICK);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		Queue<Video> videosRecientes = stringToRecientes(servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTE));
		List<PlayList> misListas = stringToPlayList(servPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS));
		

		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		usuario.setId(eUsuario.getId());
		usuario.setRecientes(videosRecientes);
		usuario.setPlayList(misListas);

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		
		playListToString(usuario.getPlayList());
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(NICK, usuario.getNick()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()),
				new Propiedad(PLAYLISTS, playListToString(usuario.getPlayList())),
				new Propiedad(RECIENTE, recientesToString(usuario.getVideosRecientes())))));//,new Propiedad(RECIENTE,codificarVideos(usuario.getVideosRecientes())))));
		return eUsuario;
	}

	public String recientesToString(Queue<Video> recientes){
		
		String linea = "";
		
		for(Video v: recientes) {
			
			linea += v.getId() + " ";
		}
		
		return linea.trim();
	}
	public Queue<Video> stringToRecientes(String linea){
		
		Queue<Video> recientes = new ArrayDeque<Video>();
		if(linea != null) {
			
			String[] vids = linea.split(" ");
			
			for(String id: vids) {
				try {
					
					TDSVideoDAO eVideo = new TDSVideoDAO();
					Video video = eVideo.get(Integer.parseInt(id));
					recientes.add(video);
				}catch(Exception e) {
					
					System.out.println(e.getMessage());
				}
			}
		}
		
		return recientes;
		
	}
	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(NICK)) {
				prop.setValor(usuario.getNick());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}
	public String playListToString(List<PlayList> listas) {
		
		String linea = "";
		
		for(PlayList pl: listas) {
			
			linea += pl.getNombre() + " ";
			
			for(Video v: pl.getVideos()) {
				
				linea += v.getId() + " ";
			}
			
			linea = linea.trim();
			linea += "$";
		}
		return linea;
		
	}
	public List<PlayList> stringToPlayList(String linea) {
		
		List<PlayList> lista = new ArrayList<PlayList>();
		if(linea != null) {
			
			String[] pls = linea.split("\\$");
			
			
			for (String cad: pls) {
				
				String[] vids = cad.split(" ");
				
				List<Video> videos = new ArrayList<Video>();
				String nombre = "";
				
				for (String cad2: vids) {
					
					if(nombre.equals("")) {
						
						nombre = cad2;
					}else {
						
						try {
							
							TDSVideoDAO eVideo = new TDSVideoDAO();
							Video video = eVideo.get(Integer.parseInt(cad2));
							videos.add(video);
							
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
						
					}
					
				}
				PlayList playList = new PlayList(nombre, videos);

				lista.add(playList);
				
			}
		}
		
		return lista;
		
	}
	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

}