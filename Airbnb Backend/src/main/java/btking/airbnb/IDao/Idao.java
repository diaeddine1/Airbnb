package btking.airbnb.IDao;

import java.util.List;

public interface Idao <T>{
    T save(T o);
    T update(T o);
    void delete(T o);
    T findById(String id);
    List<T> findAll();
}
