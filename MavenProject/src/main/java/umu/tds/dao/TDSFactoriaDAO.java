package umu.tds.dao;

public final class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {	}
	
	@Override
	public TDSUsuarioDAO getUsuarioDAO() {	
		return new TDSUsuarioDAO(); 
	}

	@Override
	public TDSVideoDAO getVideoDAO() {
		// TODO Auto-generated method stub
		return new TDSVideoDAO();
	}

	@Override
	public PlayListDAO getPlayListDAO() {
		// TODO Auto-generated method stub
		return new TDSPlayListDAO();
	}
	

}