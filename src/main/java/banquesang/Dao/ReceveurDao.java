
package banquesang.Dao;

import banquesang.enums.Urgence;
import banquesang.model.Receveur;
import java.util.List;

public interface  ReceveurDao {

    public void save(Receveur receveur) ;

    public List<Receveur> findAll () ;


    public void update(Receveur receveur) ;

    public void delete(Long id) ;

    public Receveur  findById(Long id) ;

    public void verifierSatisfaction(Receveur receveur, int donsRecus) ;
     int getBesoinPoches(Urgence urgence) ;
}
