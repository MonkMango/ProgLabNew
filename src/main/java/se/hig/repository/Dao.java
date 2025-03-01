package se.hig.repository;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T>{
    T get (int id) throws SQLException;
    List<T> getAll() throws SQLException;
    T save (T t) throws SQLException;
    T update (T t) throws SQLException;
    T delete (T t) throws SQLException;
}
