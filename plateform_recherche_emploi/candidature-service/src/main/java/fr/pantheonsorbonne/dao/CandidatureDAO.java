/*package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Candidature;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CandidatureDAO {
    @Inject
    EntityManager em;
    public void save (Candidature candidature){
        em.persist(candidature);
    }

    public Candidature getById(Long id) {
        return em.find(Candidature.class, id);
    }
}
*/
