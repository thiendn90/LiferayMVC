package ex.employee.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by toannh on 7/21/2016.
 */
public interface GenericDao<T extends Serializable> {

    void save(final T entity);
    void update(final T entity);
    void delete(final T entity);

    T findById(final long id);
    List<T> findAll();
}
