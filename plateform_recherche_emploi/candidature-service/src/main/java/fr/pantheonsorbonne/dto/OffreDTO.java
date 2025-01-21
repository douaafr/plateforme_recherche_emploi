package fr.pantheonsorbonne.dto;

public record OffreDTO(Long id, String nom, String description, String entreprise, String localisation, String typeContrat, Double salaire, String candidatureNumber) {
}
