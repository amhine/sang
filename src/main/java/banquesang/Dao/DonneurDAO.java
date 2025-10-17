package banquesang.Dao;

import banquesang.model.Donneur;
import java.util.List;

public interface DonneurDAO {

    public void save(Donneur donneur) ;

    public List<Donneur> getAllDonneurs() ;


    public void update(Donneur donneur) ;

    public void delete(Long id) ;

    public Donneur findById(Long id) ;



}
