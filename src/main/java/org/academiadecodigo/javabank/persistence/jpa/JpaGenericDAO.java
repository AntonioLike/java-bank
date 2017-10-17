package org.academiadecodigo.javabank.persistence.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.DAO;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class JpaGenericDAO<T extends AbstractModel> implements DAO<T>{
    JpaTransactionManager tm;
    JpaSessionManager sm;

    private Class<T> modelType;


    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = sm.getCurrentSession().getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }
        @Override
    public T findById(Integer id) {
            return sm.getCurrentSession().find(modelType, id);
    }

    @Override
    public T saveOrUpdate(T modelObject) {
        T savedObject = sm.getCurrentSession().merge(modelObject);
        return savedObject;
    }

    @Override
    public void delete(Integer id) {
        sm.getCurrentSession().remove(sm.getCurrentSession().find(modelType,id));
    }
}
