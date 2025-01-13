package org.example.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.dao.UtenteDAO;
import org.example.dao.UtenteJDBCImpl;

import java.io.IOException;

@WebServlet(name = "utenteServlet", value = "/utente")
public class UtenteServlet extends HttpServlet{
    private UtenteDAO dao;
    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new UtenteJDBCImpl(ip, port, dbName, userName, password);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       if (dao.checkRuolo(username, password)) {
            response.sendRedirect("gestione?username=" + username + "&password=" + password);
        } else if (dao.checkUtente(username, password)) {
            response.sendRedirect("home?username=" + username + "&password=" + password);
        } else {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
        }
    }
}
