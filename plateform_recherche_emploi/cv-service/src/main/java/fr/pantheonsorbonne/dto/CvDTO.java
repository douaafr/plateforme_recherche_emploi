package fr.pantheonsorbonne.dto;

import java.util.List;

public record CvDTO(
        String nom,
        String email,
        String telephone,
        List<String> competences,
        List<ExperienceDTO> experience,
        List<FormationDTO> formation
) {}
