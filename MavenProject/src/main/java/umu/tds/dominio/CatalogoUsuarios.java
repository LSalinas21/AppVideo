package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;

public class CatalogoUsuarios {
	//prueba commit
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	
	private HashMap<Integer, Usuario> asistentesPorID;
	private HashMap<String, Usuario> asistentesPorLogin;
	
	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	public List<Usuario> getUsuarios() {
		return new LinkedList<Usuario>(asistentesPorLogin.values());
	}
	
	public Usuario getUsuario(String login) {
		return asistentesPorLogin.get(login);
	}
	
	public Usuario getUsuario(int id) {
		return asistentesPorID.get(id);
	}
	
	private CatalogoUsuarios (){
		asistentesPorID = new HashMap<Integer, Usuario>();
		asistentesPorLogin = new HashMap<String, Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listaAsistentes = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listaAsistentes) {
				asistentesPorID.put(usuario.getId(), usuario);
				asistentesPorLogin.put(usuario.getNick(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
		
	}
	
	public void addUsuario(Usuario usuario) {
		asistentesPorID.put(usuario.getId(), usuario);
		asistentesPorLogin.put(usuario.getNick(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		asistentesPorID.remove(usuario.getId());
		asistentesPorLogin.remove(usuario.getNick());
	}

}
