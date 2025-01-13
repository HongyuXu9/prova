package org.example.dao;

import org.example.entity.Notifica;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class NotificaJDBCImpl implements NotificaDAO {
    private Connection conn;

    public NotificaJDBCImpl(String ip, String port, String dbName, String userName, String pwd) {

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
    public int insertNotifica(String username, String testo) {
        String query = "INSERT INTO notifiche (username, testo) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, testo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows;


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public ArrayList<Notifica> loadNotifica(String username) {
        String query = "SELECT * FROM notifiche WHERE username = \"" + username + "\"";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Notifica> res = new ArrayList<>();
            while (rset.next()) {
                int id = rset.getInt("id_notifica");
                String testo = rset.getString("testo");
                Timestamp data = rset.getTimestamp("data_notifica");

                LocalDateTime dataLocal = data.toLocalDateTime();
                Notifica n = new Notifica(id,username,testo, dataLocal);
                res.add(n);
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
