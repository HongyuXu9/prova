package org.example.dao;

import org.example.entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class AcquistoJDBCimpl implements AcquistoDAO {
    private Connection conn;

    public AcquistoJDBCimpl(String ip, String port, String dbName, String userName, String pwd) {

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
    public boolean checkProdotto(String username, int id){
        String query = "SELECT * FROM carrello WHERE username = \"" + username + "\" AND id_prodotto =\"" + id +"\"";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public int addProdottoC(String username, int id, int quantita) {
        if(checkProdotto(username, id)){
            String query = "UPDATE carrello SET quantita = quantita + ? WHERE username = ? AND id_prodotto = ?";
            try{
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setInt(1, quantita);
                pstmt.setString(2, username);
                pstmt.setInt(3, id);

                int affectedRows = pstmt.executeUpdate();
                return affectedRows;

            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }

        }else{
            String query = "INSERT INTO carrello (username, id_prodotto, quantita) VALUES (?, ?, ?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, username);
                pstmt.setInt(2, id);
                pstmt.setInt(3, quantita);

                int affectedRows = pstmt.executeUpdate();
                return affectedRows;


            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }

        }
    }

    @Override
    public Prodotto getProdottobyid(int id){
        String query = "SELECT * FROM prodotti WHERE id = \"" + id + "\"";
        Prodotto res = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()) {
                String nome = rset.getString(2);
                String descrizione = rset.getString(3);
                double prezzo = rset.getDouble(4);
                int disponibilita = rset.getInt(5);
                String immagine = rset.getString(6);
                String categoria = rset.getString(7);

                res = new Prodotto(id, nome, descrizione, prezzo, disponibilita, immagine, categoria);
                break;
            }

            rset.close();
            stmt.close();

            return res;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Prodotto> getProdottiUser(String username) {
        String query = "SELECT id_prodotto FROM carrello WHERE username = \"" + username + "\"";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Prodotto> res = new ArrayList<>();
            while (rset.next()) {
                int id_prodotto = rset.getInt("id_prodotto");
                Prodotto p = getProdottobyid(id_prodotto);
                res.add(p);

            }
            rset.close();
            stmt.close();

            return res;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int getQuantitabyId(int id, String username) {
        String query = "SELECT quantita FROM carrello WHERE username = \"" + username + "\" AND id_prodotto =\"" + id +"\"";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            int res = 0;
            while (rset.next()) {
                res = rset.getInt("quantita");
                break;
            }
            rset.close();
            stmt.close();

            return res;
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteProdotto(String username, int id_prodotto){
        String query = "DELETE FROM carrello WHERE username = ? AND id_prodotto = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setInt(2, id_prodotto);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;


        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


}
