package org.academiadecodigo.javabank.persistence.jpa.models;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.CustomerDAO;
import org.academiadecodigo.javabank.persistence.jpa.JpaGenericDAO;

public class JpaCustomerDAO extends JpaGenericDAO<Customer> implements CustomerDAO {
}
