package dao;

import java.util.List;

public class Dao {
    T find(long id);
    List<T> findAll();
    void save(T o);
    void delete(T o);
    void update(T o);
}
