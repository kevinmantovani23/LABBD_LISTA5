package net.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.dao.EntradaSaidaDao;
import net.dao.GenericDao;
import net.model.EntradaSaida;

/**
 * Servlet implementation class EntradaSaidaServlet
 */
@WebServlet("/inserir")
public class EntradaSaidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private GenericDao gDao = new GenericDao();
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/insertentradasaida.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo_transacao = Integer.parseInt(request.getParameter("codigo_transacao"));
		int codigo_produto = Integer.parseInt(request.getParameter("codigo_produto"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		String tipo = request.getParameter("tipo");
		
		
		EntradaSaida es = new EntradaSaida();
		es.setCodigo_transacao(codigo_transacao);
		es.setCodigo_produto(codigo_produto);
		es.setQuantidade(quantidade);
		es.setTipo(tipo);
		
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
