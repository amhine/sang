package banquesang.service;

import banquesang.Dao.DonneurDaoImp;
import banquesang.enums.StatusDisponibilite;
import banquesang.model.Donneur;
import banquesang.model.Medical;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DonneurService {
    private DonneurDaoImp donneurDAO = new DonneurDaoImp();

    public void creerDonneur(Donneur donneur) {
        donneurDAO.save(donneur);
    }

    public List<Donneur> getAllDonneurs() {
        return donneurDAO.getAllDonneurs();
    }

    public void modifierDonneur(Donneur donneur) {
        donneurDAO.update(donneur);
    }

    public void supprimerDonneur(Long id) {
        donneurDAO.delete(id);
    }

    public Donneur getDonneurById(Long id) {
        return donneurDAO.findById(id);
    }

    public void mettreAJourDisponibilite(Donneur d) {
        boolean ageOk = true;
        boolean poidsOk = true;
        boolean medicalOk = true;

        // Vérifier l'âge entre 18 et 65 ans
        if (d.getDateNaissance() != null) {
            int age = Period.between(d.getDateNaissance(), LocalDate.now()).getYears();
            ageOk = (age >= 18 && age <= 65);
        }

        // Vérifier le poids minimum
        poidsOk = d.getPoids() >= 50;

        // Vérifier les contre-indications médicales
        Medical m = d.getMedical();
        if (m != null) {
            medicalOk = !(m.isHepatiteB() || m.isHepatiteC() || m.isVih() || m.isDiabete() || m.isGrossesse() || m.isAllaitement());
        }

        // Déterminer le statut
        if (!ageOk || !poidsOk || !medicalOk) {
            d.setStatusDisponibilite(StatusDisponibilite.Non_eligible);
        } else {
            d.setStatusDisponibilite(StatusDisponibilite.Disponible);
        }
    }
}
