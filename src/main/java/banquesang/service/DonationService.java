package banquesang.service;

import banquesang.Dao.DonationDao;
import banquesang.Dao.DonationDaoImpl;
import banquesang.model.Donneur;
import banquesang.model.Receveur;

import java.util.List;

public class DonationService {

    private final DonationDao donationDao;

    public DonationService() {
        this.donationDao = new DonationDaoImpl();
    }

    public List<Donneur> getDonneursCompatibles(Receveur receveur) {
        return donationDao.getDonneursCompatibles(receveur);
    }

    public List<Receveur> getReceveursCompatibles(Donneur donneur) {
        return donationDao.getReceveursCompatibles(donneur);
    }

    public boolean associerDonneurReceveur(Donneur donneur, Receveur receveur) {
        return donationDao.associerDonneurReceveur(donneur, receveur);
    }
}
