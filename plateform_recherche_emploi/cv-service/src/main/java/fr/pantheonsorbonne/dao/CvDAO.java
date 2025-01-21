package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Cv;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CvDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Cv save(Cv cv) {
        em.persist(cv);
        return cv;
    }

    public Cv findById(Long id) {
        return em.find(Cv.class, id);
    }

    @Transactional
    public Cv update(Cv cv) {
        return em.merge(cv);
    }

    @Transactional
    public void deleteById(Long id) {
        Cv cv = findById(id);
        if (cv != null) {
            em.remove(cv);
        }
    }
}
