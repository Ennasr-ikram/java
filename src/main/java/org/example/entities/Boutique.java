package org.example.entities;

public class Boutique {
    private int id;
    private String nom;
    private String adress;
    private int nbvend;
    private String tel;
    private String description ;


    public Boutique() {
    }

    public Boutique(String nom, String adress, int nbvend, String tel, String description) {
        this.nom = nom;
        this.adress = adress;
        this.nbvend = nbvend;
        this.tel = tel;
        this.description=description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnom() {
        return nom;
    }

    public String setnom(String nom) {this.nom = nom;
        return nom;
    }

    public String getadress() {
        return adress;
    }
    public String setadress(String adress) {
        this.adress = adress;
        return adress;
    }


    public void setnbvend(int nbvend) {
        this.nbvend= nbvend;
    }

    public int getnbvend() {
        return nbvend;
    }

    public void settel(String tel) {
        this.tel = tel;
    }

    public String gettel() {
        return tel;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return id+"/ "+nom+"/ "+adress+"/ "+nbvend+"/ "+tel+"/ "+description;
    }
}