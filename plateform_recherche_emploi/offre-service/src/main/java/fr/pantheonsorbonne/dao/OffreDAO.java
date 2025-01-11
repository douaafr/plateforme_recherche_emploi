package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Offre;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class OffreDAO {

    @Inject
    EntityManager em;
    public boolean isOffrePresent (Long offreId){
        return !em.createQuery("SELECT o FROM Offre o WHERE o.id = :id")
                .setParameter("id", offreId)
                .getResultList().isEmpty();

    }

    public void saveOffre(Offre offre) {
        em.persist(offre);
    }

    public Offre getById(Long id) {
        return em.find(Offre.class, id);
    }
}
