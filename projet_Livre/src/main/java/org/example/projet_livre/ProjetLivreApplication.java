package org.example.projet_livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.example.projet_livre.entities.Livre;
import org.example.projet_livre.entities.Auteur;

@SpringBootApplication
public class ProjetLivreApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ProjetLivreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Exposer les IDs pour Livre et Auteur
        repositoryRestConfiguration.exposeIdsFor(Livre.class, Auteur.class);
        System.out.println("✅ Configuration Spring Data REST activée");
    }
}

