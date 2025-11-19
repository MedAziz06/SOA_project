package org.example.projet_livre;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.projet_livre.entities.Auteur;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.repos.AuteurRepository;
import org.example.projet_livre.repos.LivreRepository;

@SpringBootTest
class ProjetLivreApplicationTests {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    // ========== TESTS DE BASE ==========

    @Test
    public void testCreateAuteur() {
        Auteur auteur = new Auteur();
        auteur.setNomAuteur("Hugo");
        auteur.setPrenomAuteur("Victor");
        auteur.setNationalite("Français");
        auteurRepository.save(auteur);
        System.out.println("Auteur créé : " + auteur);
    }

    @Test
    public void testCreateLivreAvecAuteur() {
        Auteur auteur = auteurRepository.findById(1L).orElse(null);
        if (auteur != null) {
            Livre livre = new Livre();
            livre.setNomLivre("Les Misérables");
            livre.setPrixLivre(25.50);
            livre.setDateCreation(new Date());
            livre.setAuteur(auteur);
            livreRepository.save(livre);
            System.out.println("Livre créé : " + livre.getNomLivre());
        } else {
            System.out.println("Auteur non trouvé! Créez d'abord un auteur avec testCreateAuteur()");
        }
    }

    // ========== RECHERCHE PAR NOM ==========

    @Test
    public void testFindByNomLivre() {
        List<Livre> livres = livreRepository.findByNomLivre("Les Misérables");
        System.out.println("=== Recherche par nom exact ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    @Test
    public void testFindByNomLivreContains() {
        List<Livre> livres = livreRepository.findByNomLivreContains("Les");
        System.out.println("=== Recherche par nom contenant 'Les' ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    // ========== RECHERCHE PAR NOM ET PRIX ==========

    @Test
    public void testFindByNomPrix() {
        List<Livre> livres = livreRepository.findByNomPrix("Les", 20.0);
        System.out.println("=== Livres contenant 'Les' et prix > 20€ ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    // ========== RECHERCHE PAR AUTEUR ==========

    @Test
    public void testFindByAuteur() {
        Auteur auteur = new Auteur();
        auteur.setIdAuteur(1L);
        List<Livre> livres = livreRepository.findByAuteur(auteur);
        System.out.println("=== Livres de l'auteur ID=1 ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    @Test
    public void testFindByAuteurIdAuteur() {
        List<Livre> livres = livreRepository.findByAuteurIdAuteur(1L);
        System.out.println("=== Livres de l'auteur ID=1 (méthode simplifiée) ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - Auteur: " +
                    l.getAuteur().getNomAuteur() + " " +
                    l.getAuteur().getPrenomAuteur());
        }
    }

    // ========== TRI DES DONNÉES ==========

    @Test
    public void testFindByOrderByNomLivreAsc() {
        List<Livre> livres = livreRepository.findByOrderByNomLivreAsc();
        System.out.println("=== Livres triés par nom (A-Z) ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    @Test
    public void testTrierLivresNomsPrix() {
        List<Livre> livres = livreRepository.trierLivresNomsPrix();
        System.out.println("=== Livres triés par nom (A-Z) et prix (DESC) ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    // ========== RECHERCHE PAR PRIX ==========

    @Test
    public void testFindByPrixGreaterThan() {
        List<Livre> livres = livreRepository.findByPrixLivreGreaterThan(20.0);
        System.out.println("=== Livres avec prix > 20€ ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    @Test
    public void testFindByPrixBetween() {
        List<Livre> livres = livreRepository.findByPrixLivreBetween(15.0, 25.0);
        System.out.println("=== Livres entre 15€ et 25€ ===");
        for (Livre l : livres) {
            System.out.println(l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    // ========== OPÉRATIONS CRUD DE BASE ==========

    @Test
    public void testFindLivre() {
        Livre l = livreRepository.findById(1L).orElse(null);
        if (l != null) {
            System.out.println(l);
        } else {
            System.out.println("Livre non trouvé!");
        }
    }

    @Test
    public void testUpdateLivre() {
        Livre l = livreRepository.findById(1L).orElse(null);
        if (l != null) {
            l.setPrixLivre(30.00);
            livreRepository.save(l);
            System.out.println("Livre mis à jour : " + l.getNomLivre() + " - " + l.getPrixLivre() + "€");
        }
    }

    @Test
    public void testDeleteLivre() {
        livreRepository.deleteById(1L);
        System.out.println("Livre supprimé!");
    }

    @Test
    public void testListerTousLivres() {
        List<Livre> livres = livreRepository.findAll();
        System.out.println("=== Tous les livres ===");
        for (Livre l : livres) {
            System.out.println(l);
        }
    }

    // ========== TEST COMPLET - CRÉER DES DONNÉES DE TEST ==========

    @Test
    public void testCreerDonneesCompletes() {
        // Créer plusieurs auteurs
        Auteur hugo = new Auteur();
        hugo.setNomAuteur("Hugo");
        hugo.setPrenomAuteur("Victor");
        hugo.setNationalite("Français");
        
        Auteur orwell = new Auteur();
        orwell.setNomAuteur("Orwell");
        orwell.setPrenomAuteur("George");
        orwell.setNationalite("Britannique");
        
        auteurRepository.save(hugo);
        auteurRepository.save(orwell);

        // Créer plusieurs livres pour Hugo
        Livre livre1 = new Livre();
        livre1.setNomLivre("Les Misérables");
        livre1.setPrixLivre(25.50);
        livre1.setDateCreation(new Date());
        livre1.setAuteur(hugo);
        livreRepository.save(livre1);

        Livre livre2 = new Livre();
        livre2.setNomLivre("Notre-Dame de Paris");
        livre2.setPrixLivre(20.00);
        livre2.setDateCreation(new Date());
        livre2.setAuteur(hugo);
        livreRepository.save(livre2);

        Livre livre3 = new Livre();
        livre3.setNomLivre("Les Contemplations");
        livre3.setPrixLivre(15.00);
        livre3.setDateCreation(new Date());
        livre3.setAuteur(hugo);
        livreRepository.save(livre3);

        // Créer des livres pour Orwell
        Livre livre4 = new Livre();
        livre4.setNomLivre("1984");
        livre4.setPrixLivre(18.00);
        livre4.setDateCreation(new Date());
        livre4.setAuteur(orwell);
        livreRepository.save(livre4);

        Livre livre5 = new Livre();
        livre5.setNomLivre("La Ferme des animaux");
        livre5.setPrixLivre(12.00);
        livre5.setDateCreation(new Date());
        livre5.setAuteur(orwell);
        livreRepository.save(livre5);

        System.out.println("✅ Données de test créées avec succès!");
        System.out.println("   - 2 auteurs créés");
        System.out.println("   - 5 livres créés");
    }
}