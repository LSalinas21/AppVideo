package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface PlayListDAO {

	PlayList create(PlayList asistente);
	boolean delete(PlayList asistente);
	void update(PlayList asistente);
	PlayList get(int id);
	List<PlayList> getAll();
	void deleteAll();
}
