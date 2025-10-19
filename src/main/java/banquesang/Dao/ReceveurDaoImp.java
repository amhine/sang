package banquesang.Dao;

import banquesang.enums.ReceveurStatus;
import banquesang.enums.StatusDisponibilite;
import banquesang.enums.Urgence;
import banquesang.enums.GroupeSang;
import banquesang.model.Donation;
import banquesang.model.Donneur;
import banquesang.model.Receveur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ReceveurDaoImp implements ReceveurDao {

    private static boolean isCompatible(GroupeSang donorGS, GroupeSang receiverGS) {
        return switch (receiverGS) {
            case O_MOINS -> donorGS == GroupeSang.O_MOINS;
            case O_PLUS -> donorGS == GroupeSang.O_MOINS || donorGS == GroupeSang.O_PLUS;
            case A_MOINS -> donorGS == GroupeSang.A_MOINS || donorGS == GroupeSang.O_MOINS;
            case A_PLUS -> donorGS == GroupeSang.A_MOINS || donorGS == GroupeSang.A_PLUS ||
                    donorGS == GroupeSang.O_MOINS || donorGS == GroupeSang.O_PLUS;
            case B_MOINS -> donorGS == GroupeSang.B_MOINS || donorGS == GroupeSang.O_MOINS;
            case B_PLUS -> donorGS == GroupeSang.B_MOINS || donorGS == GroupeSang.B_PLUS ||
                    donorGS == GroupeSang.O_MOINS || donorGS == GroupeSang.O_PLUS;
            case AB_MOINS -> donorGS == GroupeSang.AB_MOINS || donorGS == GroupeSang.A_MOINS ||
                    donorGS == GroupeSang.B_MOINS || donorGS == GroupeSang.O_MOINS;
            case AB_PLUS -> true;
        };
    }

    @Override
    public void save(Receveur receveur) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(receveur);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Receveur> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Receveur> receveurs = null;
        try {
            TypedQuery<Receveur> query = em.createQuery("SELECT DISTINCT r FROM Receveur r " +
                    "LEFT JOIN FETCH r.donations d " +
                    "LEFT JOIN FETCH d.donneur", Receveur.class);
            receveurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return receveurs;
    }

    @Override
    public void update(Receveur receveur) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(receveur);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Receveur receveur = em.find(Receveur.class, id);
            if (receveur != null) {
                if (receveur.getDonations() != null) {
                    for (Donation d : receveur.getDonations()) {
                        Donneur donneur = d.getDonneur();
                        if (donneur != null) {
                            donneur.setStatusDisponibilite(StatusDisponibilite.Disponible);
                            donneur.setDonation(null);
                            em.merge(donneur);
                        }
                        em.remove(em.contains(d) ? d : em.merge(d));
                    }
                }
                em.remove(em.contains(receveur) ? receveur : em.merge(receveur));
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Receveur findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Receveur receveur = null;
        try {
            receveur = em.find(Receveur.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return receveur;
    }

    public void adjustDonations(Receveur receveur, boolean removeIncompatible, Urgence newUrgence) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            if (removeIncompatible) {
                List<Donation> donations = new ArrayList<>(em.createQuery(
                                "SELECT d FROM Donation d WHERE d.receveur.id = :id", Donation.class)
                        .setParameter("id", receveur.getId())
                        .getResultList());
                for (Donation d : donations) {
                    if (!isCompatible(d.getDonneur().getGroupeSang(), receveur.getGroupeSang())) {
                        Donneur donneur = d.getDonneur();
                        if (donneur != null) {
                            donneur.setStatusDisponibilite(StatusDisponibilite.Disponible);
                            donneur.setDonation(null);
                            em.merge(donneur);
                        }
                        em.remove(em.contains(d) ? d : em.merge(d));
                    }
                }
            }

            int needed = getBesoinPoches(newUrgence);
            TypedQuery<Long> countQuery = em.createQuery(
                    "SELECT COUNT(d) FROM Donation d WHERE d.receveur.id = :id", Long.class);
            countQuery.setParameter("id", receveur.getId());
            long currentCount = countQuery.getSingleResult();

            if (currentCount > needed) {
                TypedQuery<Donation> q = em.createQuery(
                        "SELECT d FROM Donation d WHERE d.receveur.id = :id ORDER BY d.id ASC", Donation.class);
                q.setParameter("id", receveur.getId());
                List<Donation> allRemaining = q.getResultList();
                for (int i = needed; i < allRemaining.size(); i++) {
                    Donation d = allRemaining.get(i);
                    Donneur donneur = d.getDonneur();
                    if (donneur != null) {
                        donneur.setStatusDisponibilite(StatusDisponibilite.Disponible);
                        donneur.setDonation(null);
                        em.merge(donneur);
                    }
                    em.remove(em.contains(d) ? d : em.merge(d));
                }
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public long getDonationsCount(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Long> q = em.createQuery(
                    "SELECT COUNT(d) FROM Donation d WHERE d.receveur.id = :id", Long.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public void verifierSatisfaction(Receveur receveur, int donsRecus) {
        // This method is not needed here as it's moved to service
    }

    @Override
    public int getBesoinPoches(Urgence urgence) {
        if (urgence == null) return 1;
        return switch (urgence) {
            case Critique -> 4;
            case Urgent -> 3;
            case Normal -> 1;
        };
    }
}