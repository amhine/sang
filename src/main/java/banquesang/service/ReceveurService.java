package banquesang.service;

import banquesang.enums.ReceveurStatus;
import banquesang.enums.Urgence;
import banquesang.model.Receveur;
import banquesang.Dao.ReceveurDaoImp;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReceveurService {

    private ReceveurDaoImp receveurDao = new ReceveurDaoImp();

    public ReceveurService() {
        this.receveurDao = new ReceveurDaoImp();
    }

    public void createReceveur(Receveur receveur) {
        receveur.setReceveurStatus(ReceveurStatus.En_attente);
        receveurDao.save(receveur);
    }

    public List<Receveur> getAllReceveurs() {
        return receveurDao.findAll();
    }

    public Receveur getReceveurById(Long id) {
        return receveurDao.findById(id);
    }

    public void updateReceveur(Receveur receveur) {
        receveurDao.update(receveur);
    }

    public void deleteReceveur(Long id) {
        receveurDao.delete(id);
    }
    public void verifierSatisfaction(Receveur receveur, int donsRecus) {
        int besoin = getBesoinPoches(receveur.getUrgence());

        if (donsRecus >= besoin) {
            receveur.setReceveurStatus(ReceveurStatus.Satisfait);
            receveur.setDisponible(false);
            receveurDao.update(receveur);
        }
    }
    private int getBesoinPoches(Urgence urgence) {
        switch (urgence) {
            case Critique:
                return 4;
            case Urgent:
                return 3;
            default:
                return 1;
        }
    }






}
