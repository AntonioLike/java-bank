package org.academiadecodigo.javabank.persistence.mock.models;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.AccountDAO;
import org.academiadecodigo.javabank.persistence.mock.MockGenericDAO;

import java.util.List;

public class MockAccountDAO extends MockGenericDAO<Account> implements AccountDAO {
}
