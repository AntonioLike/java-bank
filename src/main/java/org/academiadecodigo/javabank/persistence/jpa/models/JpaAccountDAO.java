package org.academiadecodigo.javabank.persistence.jpa.models;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.AccountDAO;
import org.academiadecodigo.javabank.persistence.jpa.JpaGenericDAO;

public class JpaAccountDAO extends JpaGenericDAO<Account> implements AccountDAO{
}
