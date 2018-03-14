package com.agenda.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nume;
    @Column
    private String prenume;
    @Column
    private String email;
    @Column(name = "numar_de_telefon")
    private String numarDeTelefon;
    @Column
    private String tara;

    public Item() {
    }

    public Item(String nume, String prenume, String email, String numarDeTelefon, String tara) {
        this(null, nume, prenume, email, numarDeTelefon, tara);
    }

    public Item(Long id, String nume, String prenume, String email, String numarDeTelefon, String tara) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.numarDeTelefon = numarDeTelefon;
        this.tara = tara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumarDeTelefon() {
        return numarDeTelefon;
    }

    public void setNumarDeTelefon(String numarDeTelefon) {
        this.numarDeTelefon = numarDeTelefon;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }
}
