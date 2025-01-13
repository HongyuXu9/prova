package org.example.dao;

import org.example.entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoJDBCimpl implements ProdottoDAO {

    private Connection conn;

    public ProdottoJDBCimpl(String ip, String port, String dbName, String userName, String pwd) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":" + port + "/" + dbName
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Prodotto> searchProdotto(String nome) {

        String query = "SELECT id, nome, descrizione, prezzo, disponibilita, immagine, categoria FROM prodotti WHERE nome = \"" + nome + "\"";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Prodotto> res = new ArrayList<>();

            while (rset.next()) {
                int id = rset.getInt(1);
                String descrizione = rset.getString(3);
                double prezzo = rset.getDouble(4);
                int disponibilita = rset.getInt(5);
                String immagine = rset.getString(6);
                String categoria = rset.getString(7);


                Prodotto prodotto = new Prodotto(id, nome, descrizione, prezzo, disponibilita, immagine, categoria);
                res.add(prodotto);
            }

            rset.close();
            stmt.close();

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<Prodotto> loadProdotti() {

        String query = "SELECT * FROM prodotti";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Prodotto> res = new ArrayList<>();

            while (rset.next()) {
                int id = rset.getInt(1);
                String nome = rset.getString(2);
                String descrizione = rset.getString(3);
                double prezzo = rset.getDouble(4);
                int disponibilita = rset.getInt(5);
                String immagine = rset.getString(6);
                String categoria = rset.getString(7);


                Prodotto prodotto = new Prodotto(id, nome, descrizione, prezzo, disponibilita, immagine, categoria);
                res.add(prodotto);
            }

            rset.close();
            stmt.close();

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
