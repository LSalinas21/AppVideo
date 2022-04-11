package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Video;

public interface VideoDAO {

	void create(Video existente);
	boolean delete(Video existente);
	void update(Video existente);
	Video get(int id);
	List<Video> getAll();
}
