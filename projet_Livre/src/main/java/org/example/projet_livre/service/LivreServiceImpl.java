package org.example.projet_livre.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.entities.Auteur;
import org.example.projet_livre.repos.LivreRepository;

@Service
public class LivreServiceImpl implements LivreService {
    
    @Autowired
    LivreRepository livreRepository;

    @Override
    public Livre saveLivre(Livre l) {
        return livreRepository.save(l);
    }

    @Override
    public Livre updateLivre(Livre l) {
        return livreRepository.save(l);
    }

    @Override
    public void deleteLivre(Livre l) {
        livreRepository.delete(l);
    }

    @Override
    public void deleteLivreById(Long id) {
        livreRepository.deleteById(id);
    }

    @Override
    public Livre getLivre(Long id) {
        return livreRepository.findById(id).get();
    }

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public List<Livre> findByNomLivre(String nom) {
        return livreRepository.findByNomLivre(nom);
    }

    @Override
    public List<Livre> findByNomLivreContains(String nom) {
        return livreRepository.findByNomLivreContains(nom);
    }

    @Override
    public List<Livre> findByNomPrix(String nom, Double prix) {
        return livreRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Livre> findByAuteur(Auteur auteur) {
        return livreRepository.findByAuteur(auteur);
    }

    @Override
    public List<Livre> findByAuteurIdAuteur(Long id) {
        return livreRepository.findByAuteurIdAuteur(id);
    }

    @Override
    public List<Livre> findByOrderByNomLivreAsc() {
        return livreRepository.findByOrderByNomLivreAsc();
    }

    @Override
    public List<Livre> trierLivresNomsPrix() {
        return livreRepository.trierLivresNomsPrix();
    }
}