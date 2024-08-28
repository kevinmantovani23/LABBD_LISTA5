package net.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import net.model.EntradaSaida;

public class EntradaSaidaDao implements ICRUDDao<EntradaSaida>{

	private GenericDao gDao;
	
	public EntradaSaidaDao(GenericDao gDao) {
		this.gDao = gDao;
	}
	
	private String callEntradaSaida(EntradaSaida t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_entradasaida ?, ?, ?, ?, ?}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, t.getTipo());
		cs.setInt(2, t.getCodigo_transacao());
		cs.setInt(3, t.getCodigo_produto());
		cs.setInt(4, t.getQuantidade());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		
		cs.close();
		c.close();
		
		return saida;
	}
	
	@Override
	public String insert(EntradaSaida t) throws SQLException, ClassNotFoundException {
		
		return callEntradaSaida(t);
	}

	@Override
	public String update(EntradaSaida t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(EntradaSaida t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntradaSaida select(EntradaSaida t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntradaSaida> list() throws SQLException, ClassNotFoundException {
		
		return null;
	}

}
