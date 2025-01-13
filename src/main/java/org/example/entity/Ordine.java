package org.example.entity;

public class Ordine {
    private int id_prodotto;
    private String username;
    private int quantita;
    private String stato;
    private int id_ordine;

    public Ordine(int id_prodotto, String username, int quantita, String stato, int id_ordine) {
        this.id_prodotto = id_prodotto;
        this.username = username;
        this.quantita = quantita;
        this.stato = stato;
        this.id_ordine = id_ordine;
    }
    public int getId_prodotto() {
        return id_prodotto;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;

    }
    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    public String getStato() {
        return stato;
    }
    public int getId_ordine() {
        return id_ordine;
    }

    public String toString(){
        return id_ordine + "\t" + username + "\t" + quantita + "\t" + stato;
    }


}
