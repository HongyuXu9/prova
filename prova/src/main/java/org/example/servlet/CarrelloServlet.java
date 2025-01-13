package org.example.servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.dao.*;
import org.example.entity.Prodotto;

public class CarrelloServlet extends HttpServlet {
    private AcquistoDAO dao;
    private UtenteDAO utenteDAO;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");


        dao = new AcquistoJDBCimpl(ip, port, dbName, userName, password);
        utenteDAO = new UtenteJDBCImpl(ip, port, dbName, userName, password);

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        ArrayList<Prodotto> risultati = dao.getProdottiUser(username);
        request.setAttribute("risultati", risultati);
        request.getRequestDispatcher("carrello.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int prodottoId = Integer.parseInt(request.getParameter("id"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        if (username == null || password == null || !utenteDAO.checkUtente(username, password)) {
            response.sendRedirect("login.jsp?error=Credenziali non valide");
            return;
        }

        int result = dao.addProdottoC(username, prodottoId, quantita);
        System.out.println(result);
        if (result== -1) {
            response.setStatus(500);
            return;
        }
        ArrayList<Prodotto> risultati = dao.getProdottiUser(username);
        ArrayList<Integer> quantitaP = new ArrayList<>();
        for(Prodotto p : risultati){
            quantitaP.add(dao.getQuantitabyId(p.getId_prodotto(), username));
        }

        request.setAttribute("risultati", risultati);
        request.setAttribute("quantitaP", quantitaP);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("carrello.jsp").forward(request, response);

    }

}

