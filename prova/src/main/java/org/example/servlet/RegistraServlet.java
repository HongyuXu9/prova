package org.example.servlet;

import jakarta.servlet.http.*;
import org.example.dao.UtenteDAO;
import org.example.dao.UtenteJDBCImpl;

import java.io.IOException;

public class RegistraServlet extends HttpServlet{
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
        String ruolo = request.getParameter("ruolo");

        if (username != null && password != null && ruolo != null) {
            int result = dao.registra(username, password, ruolo);
            if (result == -1) {
                response.sendRedirect("registra.jsp?error=Registrazione non è andato a buon fine");
                return;
            }
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("registra.jsp?error=Registrazione non e andato a buon fine");
        }
    }
}
