package fr.pantheonsorbonne.entity;

import jakarta.persistence.*;


@Entity
@Table(name="candidature")
public class Candidature {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    String candidatId;

    @Column(nullable = false)
    Long offreId;

    String statut;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(String candidatId) {
        this.candidatId = candidatId;
    }

    public Long getOffreId() {
        return offreId;
    }

    public void setOffreId(Long offreId) {
        this.offreId = offreId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
