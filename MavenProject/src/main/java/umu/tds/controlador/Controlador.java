package umu.tds.controlador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.UsuarioDAO;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.CatalogoVideos;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.Video;

public class Controlador {
	
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;
	private static final int SIZE = 5;
	Queue<Video> recientes = new ArrayDeque<Video>(SIZE);
	
	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
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
	public ArrayList<Video> buscarMisListas(String lista){
		
		return usuarioActual.getLista(lista);
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

}
