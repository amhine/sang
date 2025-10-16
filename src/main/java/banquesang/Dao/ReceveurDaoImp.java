package banquesang.Dao;

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
            TypedQuery<Receveur> query = em.createQuery("SELECT r FROM Receveur r", Receveur.class);
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
                em.remove(receveur);
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
}
