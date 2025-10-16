
package banquesang.Dao;

import banquesang.model.Donneur;
import banquesang.model.Receveur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public interface  ReceveurDao {

    public void save(Receveur receveur) ;

    public List<Receveur> findAll () ;


    public void update(Receveur receveur) ;

    public void delete(Long id) ;

    public Receveur  findById(Long id) ;



}
