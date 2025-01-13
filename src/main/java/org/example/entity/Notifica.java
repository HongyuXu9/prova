package org.example.entity;


import java.time.LocalDateTime;

public class Notifica {

    private int idNotifica;
    private String username;
    private String testo;
    private LocalDateTime dataNotifica;

    public Notifica(int idNotifica, String username, String testo, LocalDateTime dataNotifica) {
        this.idNotifica = idNotifica;
        this.username = username;
        this.testo = testo;
        this.dataNotifica = dataNotifica;
    }

    public int getIdNotifica() {
        return idNotifica;
    }

    public void setIdNotifica(int idNotifica) {
        this.idNotifica = idNotifica;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


    public LocalDateTime getDataNotifica() {
        return dataNotifica;
    }

    public void setDataNotifica(LocalDateTime dataNotifica) {
        this.dataNotifica = dataNotifica;
    }

    @Override
    public String toString() {
        return "Notifica{" +
                "idNotifica=" + idNotifica +
                ", username='" + username + '\'' +
                ", testo='" + testo + '\'' +
                ", dataNotifica=" + dataNotifica +
                '}';
    }

}


