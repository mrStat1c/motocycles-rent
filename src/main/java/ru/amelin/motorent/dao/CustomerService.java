package ru.amelin.motorent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.amelin.motorent.models.Customer;

import java.util.List;

@Component
public class CustomerService {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper rowMapper;

    @Autowired
    public CustomerService(JdbcTemplate jdbcTemplate, CustomerRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        return this.jdbcTemplate.query(sql, this.rowMapper);
    }

    public Customer get(int id) {
        String sql = "SELECT * FROM customer WHERE id=?";
        return this.jdbcTemplate.query(sql, new Object[]{id}, this.rowMapper)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void add(Customer customer) {
        String sql = "INSERT INTO customer (name, age, driver_exp, driver_lic, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getAge(),
                customer.getDriveExperience(),
                customer.getDriverLicenseNumber(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, age = ?, driver_exp = ?, driver_lic = ?, email = ?, phone = ? WHERE id = ?";
        this.jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getAge(),
                customer.getDriveExperience(),
                customer.getDriverLicenseNumber(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getId()
        );
    }

    public void delete(int customerId) {
        String sql = "DELETE FROM customer WHERE id = ?";
        this.jdbcTemplate.update(sql, customerId);
    }

    public boolean exists(String driver_lic) {
        String sql = "SELECT * FROM customer WHERE driver_lic=?";
        return this.jdbcTemplate.query(sql, new Object[]{driver_lic}, this.rowMapper).size() > 0;
    }
}

