package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.*;
import org.example.entity.Ordine;

import java.io.IOException;
import java.util.ArrayList;

public class GestioneServlet extends HttpServlet {
    private OrdineDAO dao;
    private UtenteDAO utenteDAO;
    private NotificaDAO notificaDAO;
    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new OrdineJDBCImpl(ip, port, dbName, userName, password);
        utenteDAO = new UtenteJDBCImpl(ip, port, dbName, userName, password);
        notificaDAO = new NotificaJDBCImpl(ip, port, dbName, userName, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }
        ArrayList<Ordine> ordini = dao.getOrdini();
        request.setAttribute("ordini", ordini);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("tabellaDegliOrdiniA.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }

        String ordineId = request.getParameter("ordineId");
        String cliente = request.getParameter("cliente");
        String nuovoStato = request.getParameter("nuovoStato");

        if (ordineId != null && nuovoStato != null) {
            int id = Integer.parseInt(ordineId);
            dao.aggiornaStatoOrdine(id, nuovoStato);
            notificaDAO.insertNotifica(cliente, "Stato aggiornato");
        }
        response.sendRedirect("gestione?username=" + username + "&password=" + password);
    }

}
