package com.meslivres.usermicroservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String jwtSecret; // Clé secrète utilisée pour signer les tokens JWT
    
    @Value("${jwt.expiration}")
    private long jwtExpirationMs; // Durée de validité du token JWT (en ms)
    
    /**
     * Génère un token JWT portant le username et les rôles de l'utilisateur authentifié.
     * @param authentication objet d'authentification Spring Security
     * @return JWT signé
     */
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        // Récupère les rôles de l'utilisateur sous forme de string séparé par des virgules
        String roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        // Génère un JWT contenant le username (subject) et les rôles comme claims personnalisés
        return JWT.create()
                .withSubject(userPrincipal.getUsername()) // "sub": le nom d'utilisateur
                .withClaim("roles", roles) // Ajout du claim "roles"
                .withIssuedAt(new Date()) // Date d'émission
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Date d'expiration
                .sign(Algorithm.HMAC256(jwtSecret)); // Signature du JWT
    }
    
    /**
     * Génère un token JWT à partir du nom d'utilisateur uniquement.
     * Moins utilisé car ne contient pas le claim "roles".
     */
    public String generateTokenFromUsername(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(Algorithm.HMAC256(jwtSecret));
    }
    
    /**
     * Extrait le username contenu dans le token JWT (champ "sub").
     */
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token); // Décoder sans valider
        return decodedJWT.getSubject(); // subject = username
    }
    
    /**
     * Valide le token JWT: signature, échéance, intégrité.
     * @return true si token valide, false sinon
     */
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token); // Lance une exception si non valide
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Token JWT invalide: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Extrait la liste de rôles (en string) du champ custom "roles" du JWT.
     */
    public String getRolesFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("roles").asString();
    }
}