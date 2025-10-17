package banquesang.service;

import banquesang.Dao.DonneurDaoImp;
import banquesang.model.Donneur;
import java.util.List;

public class DonneurService {
    private DonneurDaoImp donneurDAO = new DonneurDaoImp();

    public void creerDonneur(Donneur donneur) {
        donneurDAO.save(donneur);
    }
    public List<Donneur> getAllDonneurs() {
        return donneurDAO.getAllDonneurs();
    }

    public void modifierDonneur(Donneur donneur) {
        donneurDAO.update(donneur);
    }

    public void supprimerDonneur(Long id) {
        donneurDAO.delete(id);
    }

    public Donneur getDonneurById(Long id) {
        return donneurDAO.findById(id);
    }
}
