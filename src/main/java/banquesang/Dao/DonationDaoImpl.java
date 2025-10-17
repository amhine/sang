package banquesang.Dao;

import banquesang.enums.ReceveurStatus;
import banquesang.enums.StatusDisponibilite;
import banquesang.enums.Urgence;
import banquesang.model.Donation;
import banquesang.model.Donneur;
import banquesang.model.Receveur;
import banquesang.service.CompatibiliteService;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class DonationDaoImpl implements DonationDao {

    @Override
    public List<Donneur> getDonneursCompatibles(Receveur receveur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<Donneur> donneurs = em.createQuery(
                            "SELECT DISTINCT d FROM Donneur d " +
                                    "LEFT JOIN FETCH d.donation " +
                                    "WHERE d.statusDisponibilite = :disponible",
                            Donneur.class)
                    .setParameter("disponible", StatusDisponibilite.Disponible)
                    .getResultList();

            return donneurs.stream()
                    .filter(d -> d.getDonation() == null)
                    .filter(d -> CompatibiliteService.isCompatible(
                            d.getGroupeSang(),
                            receveur.getGroupeSang()))
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Receveur> getReceveursCompatibles(Donneur donneur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<Receveur> receveurs = em.createQuery(
                            "SELECT DISTINCT r FROM Receveur r " +
                                    "LEFT JOIN FETCH r.donations " +
                                    "WHERE r.receveurStatus != :satisfait",
                            Receveur.class)
                    .setParameter("satisfait", ReceveurStatus.Satisfait)
                    .getResultList();

            return receveurs.stream()
                    .filter(r -> r.getDonations().size() < getNombrePochesParUrgence(r.getUrgence()))
                    .filter(r -> CompatibiliteService.isCompatible(
                            donneur.getGroupeSang(),
                            r.getGroupeSang()))
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean associerDonneurReceveur(Donneur donneur, Receveur receveur) {
        if (donneur == null || receveur == null) return false;
        if (!CompatibiliteService.isCompatible(donneur.getGroupeSang(), receveur.getGroupeSang())) return false;
        if (donneur.getStatusDisponibilite() != StatusDisponibilite.Disponible) return false;
        if (receveur.getReceveurStatus() == ReceveurStatus.Satisfait) return false;

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Receveur receveurManaged = em.createQuery(
                            "SELECT r FROM Receveur r LEFT JOIN FETCH r.donations WHERE r.id = :id",
                            Receveur.class)
                    .setParameter("id", receveur.getId())
                    .getSingleResult();

            int nombrePochesNecessaires = getNombrePochesParUrgence(receveurManaged.getUrgence());
            if (receveurManaged.getDonations().size() >= nombrePochesNecessaires) {
                em.getTransaction().rollback();
                return false;
            }

            Donation donation = new Donation();
            donation.setDonneur(donneur);
            donation.setReceveur(receveurManaged);

            receveurManaged.getDonations().add(donation);
            donneur.setDonation(donation);
            donneur.setStatusDisponibilite(StatusDisponibilite.Non_disponible);

            if (receveurManaged.getDonations().size() >= nombrePochesNecessaires) {
                receveurManaged.setReceveurStatus(ReceveurStatus.Satisfait);
            }

            em.persist(donation);
            em.merge(donneur);
            em.merge(receveurManaged);

            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public int getNombrePochesParUrgence(Urgence urgence) {
        if (urgence == null) return 1;
        return switch (urgence) {
            case Critique -> 4;
            case Urgent -> 3;
            case Normal -> 1;
        };
    }
}
