package org.example.projet_livre.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin("*")
public class AuteurRESTController {
    
    @Autowired
    AuteurRepository auteurRepository;
    
    // GET tous les auteurs
    @RequestMapping(method = RequestMethod.GET)
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }
    
    // GET un auteur par ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Auteur getAuteurById(@PathVariable("id") Long id) {
        return auteurRepository.findById(id).get();
    }
    
    // POST créer un auteur
    @RequestMapping(method = RequestMethod.POST)
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurRepository.save(auteur);
    }
    
    // PUT modifier un auteur
    @RequestMapping(method = RequestMethod.PUT)
    public Auteur updateAuteur(@RequestBody Auteur auteur) {
        return auteurRepository.save(auteur);
    }
    
    // DELETE supprimer un auteur
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAuteur(@PathVariable("id") Long id) {
        auteurRepository.deleteById(id);
    }
}