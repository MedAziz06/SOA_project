package org.example.projet_livre.entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;
    
    private String nomLivre;
    private Double prixLivre;
    private Date dateCreation;
    
    @ManyToOne
    private Auteur auteur;

    // Constructeur par défaut
    public Livre() {
    }

    // Constructeur avec paramètres
    public Livre(String nomLivre, Double prixLivre, Date dateCreation, Auteur auteur) {
        this.nomLivre = nomLivre;
        this.prixLivre = prixLivre;
        this.dateCreation = dateCreation;
        this.auteur = auteur;
    }

    // Getters et Setters
    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }

    public Double getPrixLivre() {
        return prixLivre;
    }

    public void setPrixLivre(Double prixLivre) {
        this.prixLivre = prixLivre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Livre [idLivre=" + idLivre + 
               ", nomLivre=" + nomLivre + 
               ", prixLivre=" + prixLivre + 
               ", dateCreation=" + dateCreation + "]";
    }
}