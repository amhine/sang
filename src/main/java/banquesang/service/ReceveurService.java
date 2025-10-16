package banquesang.service;

import banquesang.model.Receveur;
import banquesang.Dao.ReceveurDaoImp;
import java.util.List;

public class ReceveurService {

    private ReceveurDaoImp receveurDao;

    public ReceveurService() {
        this.receveurDao = new ReceveurDaoImp();
    }

    public void createReceveur(Receveur receveur) {
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
}
