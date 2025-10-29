package org.example.projet_livre.service;

import java.util.List;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.entities.Auteur;

public interface LivreService {
    Livre saveLivre(Livre l);
    Livre updateLivre(Livre l);
    void deleteLivre(Livre l);
    void deleteLivreById(Long id);
    Livre getLivre(Long id);
    List<Livre> getAllLivres();
    
    List<Livre> findByNomLivre(String nom);
    List<Livre> findByNomLivreContains(String nom);
    List<Livre> findByNomPrix(String nom, Double prix);
    List<Livre> findByAuteur(Auteur auteur);
    List<Livre> findByAuteurIdAuteur(Long id);
    List<Livre> findByOrderByNomLivreAsc();
    List<Livre> trierLivresNomsPrix();
}