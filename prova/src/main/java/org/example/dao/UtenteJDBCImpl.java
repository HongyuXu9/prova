package org.example.dao;

import java.sql.*;
import java.util.Objects;

public class UtenteJDBCImpl implements UtenteDAO {

    private Connection conn;
    public UtenteJDBCImpl(String ip, String port, String dbName, String userName, String pwd){
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
    public boolean checkUtente(String username, String password){
        String query = "SELECT * FROM utenti WHERE username = \"" + username + "\" AND password =\"" + password +"\"";
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
    public boolean checkRuolo(String username, String password) {
        String query = "SELECT * FROM utenti WHERE username = \"" + username + "\" AND password =\"" + password +"\"";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                if ("amministratore".equals(rs.getString("ruolo"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public int registra(String username, String password, String ruolo) {
        String query = "INSERT INTO utenti (username, password, ruolo) VALUES (?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, ruolo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


}
