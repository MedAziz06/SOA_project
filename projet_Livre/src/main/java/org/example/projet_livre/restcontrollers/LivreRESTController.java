package org.example.projet_livre.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.service.LivreService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LivreRESTController {
    
    @Autowired
    LivreService livreService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Livre> getAllLivres() {
        return livreService.getAllLivres();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Livre getLivreById(@PathVariable("id") Long id) {
        return livreService.getLivre(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.saveLivre(livre);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Livre updateLivre(@RequestBody Livre livre) {
        return livreService.updateLivre(livre);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLivre(@PathVariable("id") Long id) {
        livreService.deleteLivreById(id);
    }

    @RequestMapping(value = "/livresaut/{idAut}", method = RequestMethod.GET)
    public List<Livre> getLivresByAutId(@PathVariable("idAut") Long idAut) {
        return livreService.findByAuteurIdAuteur(idAut);
    }

    // NOUVELLE MÉTHODE : Recherche par nom
    @RequestMapping(value = "/livresByName/{nom}", method = RequestMethod.GET)
    public List<Livre> findByNomLivreContains(@PathVariable("nom") String nom) {
        return livreService.findByNomLivreContains(nom);
    }
}