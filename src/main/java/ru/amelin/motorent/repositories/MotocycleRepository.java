package ru.amelin.motorent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amelin.motorent.models.Customer;
import ru.amelin.motorent.models.Motocycle;

import java.util.List;
import java.util.Optional;

/**
 * Repository для объектов Motocycle
 */
@Repository
public interface MotocycleRepository extends JpaRepository<Motocycle, Integer> {

       List<Motocycle> findByCustomer(Customer customer);

       Optional<Motocycle> findByVin(String vin);
}
