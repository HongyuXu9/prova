package org.example.entity;


import org.json.JSONObject;

public class
Prodotto {
    private int id_prodotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int disponibilita;
    private String immagine;
    private String categoria;


    public Prodotto(int id_prodotto, String nome,  String descrizione, double prezzo, int disponibilita, String immagine, String categoria ) {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
        this.immagine = immagine;
        this.categoria = categoria;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }
    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public int getDisponibilita() {
        return disponibilita;
    }
    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }
    public String getImmagine() {
        return immagine;
    }
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return  "{" +
                "id_prodotto=" + id_prodotto +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", disponibilita=" + disponibilita +
                ", immagine='" + immagine + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public String toJSONString() {
        return new JSONObject(this).toString();

    }
}
