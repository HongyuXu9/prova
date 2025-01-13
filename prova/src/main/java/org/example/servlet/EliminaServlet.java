package org.example.servlet;
import jakarta.servlet.http.*;
import org.example.dao.AcquistoDAO;
import org.example.dao.AcquistoJDBCimpl;
import org.example.dao.UtenteJDBCImpl;

import java.io.IOException;

public class EliminaServlet extends HttpServlet {
    private AcquistoDAO dao;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new AcquistoJDBCimpl(ip, port, dbName, userName, password);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));

        dao.deleteProdotto(username, id);
        response.sendRedirect("carrello?username=" + username + "&password=" + password);
    }

}
