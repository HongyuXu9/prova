package org.example.dao;

import org.example.entity.Ordine;
import org.example.entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class OrdineJDBCImpl implements OrdineDAO{

    private Connection conn;

    public OrdineJDBCImpl(String ip, String port, String dbName, String userName, String pwd) {

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

    @Override
    public ArrayList<Ordine> ordiniEffettuati(String username) {
        String query = "SELECT * FROM ordini Where username=\"" + username + "\"";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            Ordine ordine = null;
            ArrayList<Ordine> res = new ArrayList<>();

            while (rset.next()) {
                int id_prodotto = rset.getInt("id_prodotto");
                String stato = rset.getString("stato");
                int quantita = rset.getInt("quantita");
                int id_ordine = rset.getInt("id_ordine");


                ordine = new Ordine(id_prodotto,username, quantita, stato, id_ordine);
                res.add(ordine);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public int aggiornaStatoOrdine(int id, String stato) {
        String query = "UPDATE ordini SET stato = ? WHERE id_ordine = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, stato);
            pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int insertProdotto(String username, int id_prodotto, int quantita) {
        String query = "INSERT INTO ordini (username, id_prodotto, quantita, stato) VALUES (?, ?, ?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setInt(2, id_prodotto);
            pstmt.setInt(3, quantita);
            pstmt.setString(4, "InAttesa");

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<Ordine> getOrdini() {
        String query = "SELECT * FROM ordini";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            Ordine ordine = null;
            ArrayList<Ordine> res = new ArrayList<>();

            while (rset.next()) {
                int id_prodotto = rset.getInt("id_prodotto");
                String stato = rset.getString("stato");
                String username = rset.getString("username");
                int quantita = rset.getInt("quantita");
                int id_ordine = rset.getInt("id_ordine");


                ordine = new Ordine(id_prodotto,username, quantita, stato, id_ordine);
                res.add(ordine);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
