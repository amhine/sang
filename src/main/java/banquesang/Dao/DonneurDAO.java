package banquesang.Dao;

import banquesang.model.Donneur;
import banquesang.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public interface DonneurDAO {

    public void save(Donneur donneur) ;

    public List<Donneur> getAllDonneurs() ;


    public void update(Donneur donneur) ;

    public void delete(Long id) ;

    public Donneur findById(Long id) ;



}
