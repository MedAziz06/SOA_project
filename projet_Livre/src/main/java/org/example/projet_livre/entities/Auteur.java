package org.example.projet_livre.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuteur;
    
    private String nomAuteur;
    private String prenomAuteur;
    private String nationalite;
    
    @JsonIgnore
    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    // Constructeur par défaut (OBLIGATOIRE pour JPA)
    public Auteur() {
    }

    // Constructeur avec paramètres
    public Auteur(String nomAuteur, String prenomAuteur, String nationalite) {
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.nationalite = nationalite;
    }

    // Getters et Setters
    public Long getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Long idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Auteur [idAuteur=" + idAuteur + 
               ", nomAuteur=" + nomAuteur + 
               ", prenomAuteur=" + prenomAuteur + 
               ", nationalite=" + nationalite + "]";
    }
}