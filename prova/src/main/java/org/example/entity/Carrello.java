package org.example.entity;

import java.util.ArrayList;

public class Carrello {
    private int idCarrello;
    private ArrayList<Prodotto> prodotti;


    public Carrello(int idCarrello) {
        this.idCarrello = idCarrello;
        this.prodotti = new ArrayList<>();

    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }


}
