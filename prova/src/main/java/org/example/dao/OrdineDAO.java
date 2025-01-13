package org.example.dao;

import org.example.entity.Ordine;
import org.example.entity.Prodotto;

import java.util.ArrayList;

public interface OrdineDAO {
    public ArrayList<Ordine> ordiniEffettuati(String username);
    public int aggiornaStatoOrdine(int id, String stato);
    public int insertProdotto(String username, int id_prodotto, int quantita);
    public ArrayList<Ordine> getOrdini();
}
