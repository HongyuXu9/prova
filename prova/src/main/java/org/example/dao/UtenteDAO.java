package org.example.dao;

public interface UtenteDAO {
    public boolean checkUtente(String username, String password);
    public boolean checkRuolo(String username, String password);
}
