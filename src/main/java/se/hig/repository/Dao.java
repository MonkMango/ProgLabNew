package se.hig.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T>{
    Optional<T> get (int id) throws SQLException;
    List<T> getAll() throws SQLException;
    Optional<T> save (T t) throws SQLException;
    Optional<T> update (T t) throws SQLException;
    Optional<T> delete (T t) throws SQLException;
}
