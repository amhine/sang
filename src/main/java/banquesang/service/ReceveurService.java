package banquesang.service;

import banquesang.enums.ReceveurStatus;
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

    public void deleteReceveur(Long id) {
        receveurDao.delete(id);
    }
    public void verifierSatisfaction(Receveur receveur, int donsRecus) {
        receveurDao.verifierSatisfaction(receveur, donsRecus);
    }
    }







