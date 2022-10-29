package ru.amelin.motorent.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.amelin.motorent.models.Customer;
import ru.amelin.motorent.models.Motocycle;

import java.util.List;

/**
 * API для работы с объектами Motocycle
 */
@Component
public class MotocycleService {

    private final SessionFactory sessionFactory;

    @Autowired
    public MotocycleService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Motocycle> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Motocycle").getResultList();
    }

    @Transactional(readOnly = true)
    public Motocycle get(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Motocycle.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Motocycle> getRentedByCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Motocycle WHERE customer = :customer")
                .setParameter("customer", customer)
                .getResultList();
    }

    @Transactional
    public void add(Motocycle motocycle) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(motocycle);
    }

    @Transactional
    public void update(Motocycle motocycle) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(motocycle);

    }

    @Transactional
    public void delete(int motoId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(session.get(Motocycle.class, motoId));
    }

    @Transactional
    public void release(int motoId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("UPDATE Motocycle SET customer = NULL WHERE id = :id")
                .setParameter("id", motoId)
                .executeUpdate();
    }

    @Transactional
    public void assign(int motoId, Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("UPDATE Motocycle SET customer = :customer WHERE id = :id")
                .setParameter("customer", customer)
                .setParameter("id", motoId)
                .executeUpdate();
    }

    @Transactional(readOnly = true)
    public boolean exists(String vin) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Motocycle WHERE vin=:vin", Motocycle.class)
                .setParameter("vin", vin)
                .uniqueResultOptional()
                .isPresent();
    }

    @Transactional(readOnly = true)
    public boolean exists(int motoId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Motocycle WHERE id=:id", Motocycle.class)
                .setParameter("id", motoId)
                .uniqueResultOptional()
                .isPresent();
    }
}
