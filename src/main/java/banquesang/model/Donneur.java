package banquesang.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import banquesang.enums.StatusDisponibilite;

@Entity
@Table(name = "donneurs")
@PrimaryKeyJoinColumn(name = "user_id")
public class Donneur extends User {

    private double poids;
    private LocalDate dernierDonation;

    @Enumerated(EnumType.STRING)
    private StatusDisponibilite statusDisponibilite;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medical_id", referencedColumnName = "id")
    private Medical medical;

    public Donneur() {
        this.medical = new Medical();
    }

    public boolean isEligible() {
        int age = calculateAge();
        if (age < 18 || age > 65) return false;
        if (poids < 50) return false;
        if (medical != null && medical.aDesContreIndications()) return false;
        return true;
    }

    @PrePersist
    @PreUpdate
    public void determinerStatut() {
        if (medical == null) {
            medical = new Medical();
        }

        if (!isEligible()) {
            this.statusDisponibilite = StatusDisponibilite.Non_eligible;
        } else if (this.statusDisponibilite == null) {
            this.statusDisponibilite = StatusDisponibilite.Disponible;
        }
    }

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }

    public LocalDate getDernierDonation() { return dernierDonation; }
    public void setDernierDonation(LocalDate dernierDonation) { this.dernierDonation = dernierDonation; }

    public StatusDisponibilite getStatusDisponibilite() { return statusDisponibilite; }
    public void setStatusDisponibilite(StatusDisponibilite statusDisponibilite) {
        this.statusDisponibilite = statusDisponibilite;
    }

    public Medical getMedical() { return medical; }
    public void setMedical(Medical medical) { this.medical = medical; }
}
