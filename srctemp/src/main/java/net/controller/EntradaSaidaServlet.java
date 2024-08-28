package net.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dao.EntradaSaidaDao;
import net.dao.GenericDao;
import net.model.EntradaSaida;

/**
 * Servlet implementation class EntradaSaidaServlet
 */
@WebServlet("/inserir")
public class EntradaSaidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private GenericDao gDao;
	private EntradaSaidaDao dao = new EntradaSaidaDao(gDao);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntradaSaidaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo_transacao = Integer.parseInt(request.getParameter("codigo_transacao"));
		int codigo_produto = Integer.parseInt(request.getParameter("codigo_produto"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		String tipo = request.getParameter("tipo");
		long valor_total = Long.parseLong(request.getParameter("valor_total"));
		
		EntradaSaida es = new EntradaSaida();
		es.setCodigo_transacao(codigo_transacao);
		es.setCodigo_produto(codigo_produto);
		es.setQuantidade(quantidade);
		es.setTipo(tipo);
		es.setValor_total(valor_total);
		
		try {
			dao.insert(es);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		response.sendRedirect("");
	}

}
