package org.example.dao;

import org.example.entity.Prodotto;

import java.util.ArrayList;

public interface ProdottoDAO {
    public ArrayList<Prodotto> searchProdotto(String nome);
    public ArrayList<Prodotto> loadProdotti();
}
