package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.CvDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CvService {
    public CvDTO getCVbyId(Long id) {
    }

    public Long saveCV(CvDTO cvDTO) { //Ajout vérification du taille de fichier et format?
    }
}
