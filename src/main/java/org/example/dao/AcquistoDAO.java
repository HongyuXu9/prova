package org.example.dao;

import org.example.entity.Prodotto;

import java.util.ArrayList;

public interface AcquistoDAO {
    public boolean checkProdotto(String username, int id);
    public int addProdottoC(String username, int id, int quantita);
    public ArrayList<Prodotto> getProdottiUser(String username);
    public Prodotto getProdottobyid(int id);
    public int getQuantitabyId(int id, String username);
    public int deleteProdotto(String username, int id_prodotto);
}
