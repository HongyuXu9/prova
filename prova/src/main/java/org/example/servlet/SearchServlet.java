package org.example.servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.dao.ProdottoDAO;
import org.example.dao.ProdottoJDBCimpl;
import org.example.dao.UtenteDAO;
import org.example.dao.UtenteJDBCImpl;
import org.example.entity.Prodotto;

public class SearchServlet extends HttpServlet {
    private ProdottoDAO dao;
    private UtenteDAO utenteDAO;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new ProdottoJDBCimpl(ip, port, dbName, userName, password);
        utenteDAO = new UtenteJDBCImpl(ip, port, dbName, userName, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nome = request.getParameter("search");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }
        ArrayList<Prodotto> risultati = dao.searchProdotto(nome);
        System.out.println(risultati);
        System.out.println(risultati.isEmpty());
        request.setAttribute("nome", nome);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("risultati", risultati);
        request.getRequestDispatcher("search.jsp").forward(request, response);

    }

    public void destroy() {
    }
}


