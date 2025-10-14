package banquesang.Dao;

import banquesang.model.Donneur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DonneurDAO {

    public void save(Donneur donneur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(donneur);
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


}
