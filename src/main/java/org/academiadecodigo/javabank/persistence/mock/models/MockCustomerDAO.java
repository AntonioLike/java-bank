package org.academiadecodigo.javabank.persistence.mock.models;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.CustomerDAO;
import org.academiadecodigo.javabank.persistence.mock.MockGenericDAO;

import java.util.List;

public class MockCustomerDAO extends MockGenericDAO<Customer> implements CustomerDAO {
}
