package org.example.servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.dao.*;
import org.example.entity.Notifica;


public class NotificaServlet extends HttpServlet {
    private NotificaDAO dao;
    private UtenteDAO utenteDAO;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new NotificaJDBCImpl(ip, port, dbName, userName, password);
        utenteDAO = new UtenteJDBCImpl(ip, port, dbName, userName, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }

        ArrayList<Notifica> notifiche = dao.loadNotifica(username);
        request.setAttribute("notifiche", notifiche);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("notifica.jsp").forward(request, response);
    }

}
