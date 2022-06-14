package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;//eliminar
import umu.tds.dominio.Usuario;

public interface UsuarioDAO {
	
	void create(Usuario asistente);
	boolean delete(Usuario asistente);
	void update(Usuario asistente);
	Usuario get(int id);
	List<Usuario> getAll();
	String playListToString(List<PlayList> lista);
	List<PlayList> stringToPlayList(String linea);
	
}