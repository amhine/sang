
package banquesang.Dao;

import banquesang.model.Donneur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DonneurDaoImp {

    public void save(Donneur donneur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(donneur);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Donneur> getAllDonneurs() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Donneur> donneurs = null;
        try {
            TypedQuery<Donneur> query = em.createQuery(
                    "SELECT d FROM Donneur d LEFT JOIN FETCH d.medical", Donneur.class
            );
            donneurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return donneurs;
    }


    public void update(Donneur donneur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(donneur);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Donneur d = em.find(Donneur.class, id);
            if (d != null) {
                em.remove(d);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Donneur findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Donneur d = null;
        try {
            d = em.find(Donneur.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return d;
    }



}
