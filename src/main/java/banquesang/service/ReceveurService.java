package banquesang.service;

import banquesang.enums.ReceveurStatus;
import banquesang.enums.GroupeSang;
import banquesang.enums.Urgence;
import banquesang.model.Receveur;
import banquesang.Dao.ReceveurDaoImp;

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

    public void updateReceveurWithAdjustments(Receveur receveur, Urgence oldUrgence, GroupeSang oldGroupeSang) {
        receveurDao.update(receveur);
        boolean groupChanged = !oldGroupeSang.equals(receveur.getGroupeSang());
        if (!oldUrgence.equals(receveur.getUrgence()) || groupChanged) {
            receveurDao.adjustDonations(receveur, groupChanged, receveur.getUrgence());
        }
        long donsRecus = receveurDao.getDonationsCount(receveur.getId());
        verifierSatisfaction(receveur, (int) donsRecus);
    }

    public void deleteReceveur(Long id) {
        receveurDao.delete(id);
    }

    public void verifierSatisfaction(Receveur receveur, int donsRecus) {
        int besoin = receveurDao.getBesoinPoches(receveur.getUrgence());
        if (donsRecus >= besoin) {
            receveur.setReceveurStatus(ReceveurStatus.Satisfait);
            receveur.setDisponible(false);
        } else {
            receveur.setReceveurStatus(ReceveurStatus.En_attente);
            receveur.setDisponible(true);
        }
        receveurDao.update(receveur);
    }
}