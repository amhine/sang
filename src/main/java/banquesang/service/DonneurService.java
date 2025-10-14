package banquesang.service;

import banquesang.Dao.DonneurDAO;
import banquesang.model.Donneur;
import java.util.List;

public class DonneurService {
    private DonneurDAO donneurDAO = new DonneurDAO();

    public void creerDonneur(Donneur donneur) {
        donneurDAO.save(donneur);
    }
    public List<Donneur> getAllDonneurs() {
        return donneurDAO.getAllDonneurs();
    }
}
