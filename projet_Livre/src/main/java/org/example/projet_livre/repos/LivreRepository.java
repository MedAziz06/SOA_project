package org.example.projet_livre.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.entities.Auteur;

@RepositoryRestResource(path = "rest")
public interface LivreRepository extends JpaRepository<Livre, Long> {
    
    // Recherche par nom
    List<Livre> findByNomLivre(String nom);
    List<Livre> findByNomLivreContains(String nom);
    
    // Recherche par nom et prix
    @Query("select l from Livre l where l.nomLivre like %:nom and l.prixLivre > :prix")
    List<Livre> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);
    
    // Recherche par auteur
    @Query("select l from Livre l where l.auteur = ?1")
    List<Livre> findByAuteur(Auteur auteur);
    
    // Recherche par ID auteur
    List<Livre> findByAuteurIdAuteur(Long id);
    
    // Tri
    List<Livre> findByOrderByNomLivreAsc();
    
    @Query("select l from Livre l order by l.nomLivre ASC, l.prixLivre DESC")
    List<Livre> trierLivresNomsPrix();
    
    // Recherche par prix (NOUVELLES MÉTHODES)
    List<Livre> findByPrixLivreGreaterThan(Double prix);
    List<Livre> findByPrixLivreBetween(Double min, Double max);
}