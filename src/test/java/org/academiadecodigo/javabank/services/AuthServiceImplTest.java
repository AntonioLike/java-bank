package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    AuthServiceImpl authService;
    CustomerService customerService;

    @Before
    public void setup() {

        authService = new AuthServiceImpl();
        customerService = mock(CustomerService.class);

        authService.setCustomerService(customerService);
    }

    @Test
    public void testAuthenticate() {

        // setup
        int fakeId = 9999;
        Customer fakeCustomer = mock(Customer.class);
        when(customerService.findById(fakeId)).thenReturn(fakeCustomer);

        // exercise
        boolean authResult = authService.authenticate(fakeId);


        // verify
        assertTrue(authResult);

    }

    @Test
    public void testAuthenticateFail() {

        // setup
        int fakeId = 9999;
        Customer fakeCustomer = mock(Customer.class);
        when(customerService.findById(fakeId)).thenReturn(null);

        // exercise
        boolean authResult = authService.authenticate(fakeId);


        // verify
        assertFalse(authResult);
    }

    @Test
    public void testGetAccessingCustomer() {

        // setup
        int fakeId = 9999;
        Customer fakeCustomer = mock(Customer.class);
        when(customerService.findById(fakeId)).thenReturn(fakeCustomer);
        when(fakeCustomer.getId()).thenReturn(fakeId);
        authService.authenticate(fakeId);

        // exercise
        Customer customer = authService.getAccessingCustomer();

        // verify
        verify(customerService, times(2)).findById(fakeId);
        assertEquals(fakeCustomer, customer);

    }
}
