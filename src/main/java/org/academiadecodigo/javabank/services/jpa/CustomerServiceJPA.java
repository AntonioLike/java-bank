package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Set;

public class CustomerServiceJPA implements CustomerService {

    private EntityManagerFactory emf;

    public CustomerServiceJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Customer customer) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); // open transaction
            em.merge(customer);
            em.getTransaction().commit(); // close transaction

        } catch (RollbackException ex) {

            em.getTransaction().rollback(); // something went wrong, make sure db is consistent

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public Customer findById(Integer id) {
        EntityManager em = emf.createEntityManager();

        try {

            // fetch a new customer by using its id
            return em.find(Customer.class, id);

        } finally {

            // make sure we close the database connection
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Customer> findAll() {

        EntityManager em = emf.createEntityManager();

        try {

            // fetch a list of customers with a given name
            return em.createQuery("from Customer", Customer.class)
                    .getResultList();

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Set<Integer> getCustomerIds() {
        return null;
    }

    @Override
    public double getBalance(int customerId) {
        return 0;
    }

    @Override
    public Set<Integer> getCustomerAccountNumbers(Integer id) {
        return null;
    }
}
