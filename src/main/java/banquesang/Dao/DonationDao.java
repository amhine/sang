package banquesang.Dao;

import banquesang.enums.Urgence;
import banquesang.model.Donneur;
import banquesang.model.Receveur;

import java.util.List;

public interface  DonationDao {
    public List<Donneur> getDonneursCompatibles(Receveur receveur) ;

    public List<Receveur> getReceveursCompatibles(Donneur donneur) ;
    public boolean associerDonneurReceveur(Donneur donneur, Receveur receveur) ;

     int getNombrePochesParUrgence(Urgence urgence) ;

    }
