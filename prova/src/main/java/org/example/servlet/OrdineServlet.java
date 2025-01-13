package org.example.servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.dao.*;
import org.example.entity.Ordine;
import org.example.entity.Prodotto;

public class OrdineServlet extends HttpServlet {
    private OrdineDAO dao;
    private AcquistoDAO acquistoDAO;
    private UtenteDAO utenteDAO;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");


        dao = new OrdineJDBCImpl(ip, port, dbName, userName, password);
        acquistoDAO = new AcquistoJDBCimpl(ip, port, dbName, userName, password);
        utenteDAO = new UtenteJDBCImpl(ip, port, dbName, userName, password);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("tabellaDegliOrdini.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] idProdotti = request.getParameterValues("idProdotto");
        String[] quantita = request.getParameterValues("quantita");

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }


        if (idProdotti != null && quantita != null) {
            for (int i = 0; i < idProdotti.length; i++) {
                int idProdotto = Integer.parseInt(idProdotti[i]);
                int quantitaProdotto = Integer.parseInt(quantita[i]);
                int result = dao.insertProdotto(username, idProdotto, quantitaProdotto);
                int result1 = acquistoDAO.deleteProdotto(username, idProdotto);
                if (result== -1 || result1 == -1) {
                    response.setStatus(500);
                    return;
                }
            }
        }
        ArrayList<Ordine> ordini = dao.ordiniEffettuati(username);
        System.out.println(username);
        request.setAttribute("ordini", ordini);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("tabellaDegliOrdini.jsp").forward(request, response);




    }

}

