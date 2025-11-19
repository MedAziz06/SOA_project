package org.example.projet_livre.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.example.projet_livre.entities.Auteur;
import org.example.projet_livre.repos.AuteurRepository;

@RestController
@RequestMapping("/api/aut")
@CrossOrigin(origins = "*")
public class AuteurRESTController {
    
    @Autowired
    AuteurRepository auteurRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Auteur> getAuteurById(@PathVariable("id") Long id) {
        try {
            Auteur auteur = auteurRepository.findById(id).orElse(null);
            if (auteur != null) {
                return ResponseEntity.ok(auteur);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        try {
            System.out.println("📥 Réception auteur: " + auteur);
            
            // Ne pas sauvegarder l'ID s'il est envoyé
            auteur.setIdAuteur(null);
            
            Auteur savedAuteur = auteurRepository.save(auteur);
            System.out.println("✅ Auteur sauvegardé: " + savedAuteur);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAuteur);
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la création de l'auteur:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Auteur> updateAuteur(@RequestBody Auteur auteur) {
        try {
            System.out.println("📝 Mise à jour auteur: " + auteur);
            Auteur updatedAuteur = auteurRepository.save(auteur);
            System.out.println("✅ Auteur mis à jour: " + updatedAuteur);
            return ResponseEntity.ok(updatedAuteur);
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la mise à jour de l'auteur:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAuteur(@PathVariable("id") Long id) {
        try {
            auteurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}