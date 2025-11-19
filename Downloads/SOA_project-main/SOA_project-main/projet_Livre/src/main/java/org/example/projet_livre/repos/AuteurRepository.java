package org.example.projet_livre.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.example.projet_livre.entities.Auteur;
@RepositoryRestResource(path = "aut")
@CrossOrigin("http://localhost:4200/")
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}