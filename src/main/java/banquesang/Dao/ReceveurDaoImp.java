package banquesang.Dao;

import banquesang.enums.ReceveurStatus;
import banquesang.enums.StatusDisponibilite;
import banquesang.enums.Urgence;
import banquesang.model.Donation;
import banquesang.model.Donneur;
import banquesang.model.Receveur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReceveurDaoImp implements ReceveurDao {

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
                // Détacher les donations des donneurs
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
    @Override
    public void verifierSatisfaction(Receveur receveur, int donsRecus) {
        int besoin = getBesoinPoches(receveur.getUrgence());

        if (donsRecus >= besoin) {
            receveur.setReceveurStatus(ReceveurStatus.Satisfait);
            receveur.setDisponible(false);
            update(receveur);
        }
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
