package net.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.model.EntradaSaida;

public class EntradaSaidaDao implements ICRUDDao<EntradaSaida> {

	private GenericDao gDao;

	public EntradaSaidaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	private String callEntradaSaida(String tipo, EntradaSaida t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_entradasaida ?, ?, ?, ?, ?}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, tipo);
		cs.setString(2, t.getTipo());
		cs.setInt(3, t.getCodigo_transacao());
		cs.setInt(4, t.getCodigo_produto());
		cs.setInt(5, t.getQuantidade());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();

		String saida = cs.getString(5);

		cs.close();
		c.close();

		return saida;
	}

	@Override
	public String insert(EntradaSaida t) throws SQLException, ClassNotFoundException {

		return callEntradaSaida("i", t);
	}

	@Override
	public String update(EntradaSaida t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return callEntradaSaida("u", t);
	}

	@Override
	public String delete(EntradaSaida t) throws SQLException, ClassNotFoundException {

		return callEntradaSaida("d", t);
	}

	@Override
	public EntradaSaida select(EntradaSaida t) throws SQLException, ClassNotFoundException {

		Connection c = gDao.getConnection();
		String sql = "SELECT codigo_transacao, codigo_produto, quantidade, valor_total "
				+ "FROM ? WHERE codigo_transacao = ?";
		PreparedStatement p = c.prepareStatement(sql);
		p.setString(1, t.getTipo());
		p.setInt(2, t.getCodigo_transacao());
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			t.setCodigo_transacao(rs.getInt("codigo_transacao"));
			t.setCodigo_produto(rs.getInt("codigo_produto"));
			t.setQuantidade(rs.getInt("quantidade"));
			t.setValor_total(rs.getLong("valor_total"));
		}
		rs.close();
		c.close();
		p.close();
		
		return t;
	}

	@Override
	public List<EntradaSaida> list() throws SQLException, ClassNotFoundException {
		List<EntradaSaida> listaEntradaSaida = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo_transacao, codigo_produto, quantidade, valor_total " + "FROM entrada";
		PreparedStatement p = c.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		while (rs.next()) {
			EntradaSaida t = new EntradaSaida();
			t.setTipo("e");
			t.setCodigo_transacao(rs.getInt("codigo_transacao"));
			t.setCodigo_produto(rs.getInt("codigo_produto"));
			t.setQuantidade(rs.getInt("quantidade"));
			t.setValor_total(rs.getLong("valor_total"));
			listaEntradaSaida.add(t);
		}
		
		sql = "SELECT codigo_transacao, codigo_produto, quantidade, valor_total " + "FROM saida";
		p = c.prepareStatement(sql);
		rs = p.executeQuery();
		while (rs.next()) {
			EntradaSaida t = new EntradaSaida();
			t.setTipo("s");
			t.setCodigo_transacao(rs.getInt("codigo_transacao"));
			t.setCodigo_produto(rs.getInt("codigo_produto"));
			t.setQuantidade(rs.getInt("quantidade"));
			t.setValor_total(rs.getLong("valor_total"));
			listaEntradaSaida.add(t);
		}
		rs.close();
		c.close();
		p.close();
		return listaEntradaSaida;
	}

}
