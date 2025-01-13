package org.example.dao;

import org.example.entity.Notifica;

import java.util.ArrayList;

public interface NotificaDAO {
    public int insertNotifica(String username, String testo);
    ArrayList<Notifica> loadNotifica(String username);
}
