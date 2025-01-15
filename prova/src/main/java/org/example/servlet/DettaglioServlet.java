package org.example.servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.dao.*;
import org.example.entity.Prodotto;

public class DettaglioServlet extends HttpServlet {
    private AcquistoDAO dao;

    public void init() {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        dao = new AcquistoJDBCimpl(ip, port, dbName, userName, password);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProdotto = Integer.parseInt(request.getParameter("id"));
        System.out.println(idProdotto);
        Prodotto prodotto = dao.getProdottobyid(idProdotto);
        request.setAttribute("prodotto", prodotto);
        request.getRequestDispatcher("dettaglio.jsp").forward(request, response);
    }
}
