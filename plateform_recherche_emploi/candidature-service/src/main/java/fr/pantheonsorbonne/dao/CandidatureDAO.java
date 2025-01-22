package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Candidature;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CandidatureDAO {

    @Inject
    EntityManager em;

    @Transactional
    public Candidature saveCandidature(Candidature candidature) {
        em.persist(candidature);
        return candidature;
    }

    public Candidature getById(Long id) {
        return em.find(Candidature.class, id);
    }


    @Transactional
    public void updateStatut(Long id, String statut) {
        Candidature candidature = em.find(Candidature.class, id);
        if (candidature != null) {
            candidature.setStatut(statut);
            em.merge(candidature);
        }


    }
}

