import banquesang.enums.Genre;
import banquesang.enums.GroupeSang;
import banquesang.enums.StatusDisponibilite;
import banquesang.model.Donneur;
import banquesang.model.Medical;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class TestJpa {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Donneur d = new Donneur();
            d.setNom("Had");
            d.setPrenom("Nih");
            d.setCin("AB145");
            d.setTelephone("0612765678");
            d.setDateNaissance(LocalDate.of(2000, 5, 2));
            d.setGenre(Genre.Femme);
            d.setGroupeSang(GroupeSang.AB_PLUS);
            d.setDisponible(true);

            d.setPoids(60);
            d.setDernierDonation(LocalDate.now().minusMonths(2));
            d.setStatusDisponibilite(StatusDisponibilite.Disponible);

            // Initialisation Medical
            d.setMedical(new Medical());

            em.persist(d);
            em.getTransaction().commit();

            System.out.println("✅ Donneur enregistré ! ID = " + d.getId());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
