package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.CvDAO;
import fr.pantheonsorbonne.dto.CvDTO;
import fr.pantheonsorbonne.dto.ExperienceDTO;
import fr.pantheonsorbonne.dto.FormationDTO;
import fr.pantheonsorbonne.entity.Cv;
import fr.pantheonsorbonne.entity.Experience;
import fr.pantheonsorbonne.entity.Formation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@ApplicationScoped
public class CvService {

    @Inject
    CvDAO cvDAO;

    public Long saveCvAndGetId(CvDTO cvDTO) {
        Cv cv = convertToEntity(cvDTO);
        Cv savedCv = cvDAO.save(cv);
        return savedCv.getId();
    }



    public CvDTO getCvById(Long id) {
        Cv cvEntity = cvDAO.findById(id);
        if (cvEntity == null) {
            return null;
        }
        return convertToDTO(cvEntity);
    }


    public Cv updateCv(Long id, CvDTO cvDTO) {
        Cv existingCv = cvDAO.findById(id);
        if (existingCv == null) {
            throw new IllegalArgumentException("CV introuvable pour l'ID : " + id);
        }

        Cv updatedCv = convertToEntity(cvDTO);
        updatedCv.setId(id); // Conserver l'ID existant
        return cvDAO.update(updatedCv);
    }


    public void deleteCv(Long id) {
        cvDAO.deleteById(id);
    }


    private Cv convertToEntity(CvDTO cvDTO) {
        Cv cv = new Cv();
        cv.setNom(cvDTO.nom());
        cv.setEmail(cvDTO.email());
        cv.setTelephone(cvDTO.telephone());
        cv.setCompetences(cvDTO.competences());
        cv.setExperience(
                cvDTO.experience().stream().map(exp -> {
                    Experience experience = new Experience();
                    experience.setPoste(exp.poste());
                    experience.setEntreprise(exp.entreprise());
                    experience.setDuree(exp.duree());
                    return experience;
                }).collect(Collectors.toList())
        );
        cv.setFormation(
                cvDTO.formation().stream().map(form -> {
                    Formation formation = new Formation();
                    formation.setDiplome(form.diplome());
                    formation.setEcole(form.ecole());
                    formation.setAnnee(form.annee());
                    return formation;
                }).collect(Collectors.toList())
        );
        return cv;
    }


    private CvDTO convertToDTO(Cv cv) {
        return new CvDTO(
                cv.getNom(),
                cv.getEmail(),
                cv.getTelephone(),
                cv.getCompetences(),
                cv.getExperience().stream().map(exp ->
                        new ExperienceDTO(exp.getPoste(), exp.getEntreprise(), exp.getDuree())
                ).collect(Collectors.toList()),
                cv.getFormation().stream().map(form ->
                        new FormationDTO(form.getDiplome(), form.getEcole(), form.getAnnee())
                ).collect(Collectors.toList())
        );
    }
}