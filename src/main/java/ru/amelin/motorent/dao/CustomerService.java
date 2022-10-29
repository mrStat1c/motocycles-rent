package ru.amelin.motorent.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.amelin.motorent.models.Customer;

import java.util.List;

/**
 * API для работы с объектами Customer
 */
@Component
public class CustomerService {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Transactional нужна для того, чтобы Spring сам открывал и закрывал транзакцию
    // readOnly = true не обязательно, но вроде как может в теории иногда ускорить работу
    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Customer", Customer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Customer get(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Transactional
    public void add(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Transactional
    public void update(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);

    }

    @Transactional
    public void delete(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(session.get(Customer.class, customerId));
    }

    @Transactional(readOnly = true)
    public boolean exists(String driverLic) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Customer WHERE driverLicenseNumber=:lic")
                .setParameter("lic", driverLic)
                .uniqueResultOptional()
                .isPresent();
    }

    @Transactional(readOnly = true)
    public boolean exists(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Customer WHERE id=:id")
                .setParameter("id", customerId)
                .uniqueResultOptional()
                .isPresent();
    }
}

