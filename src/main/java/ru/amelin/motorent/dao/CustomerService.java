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
        return this.jdbcTemplate.query(sql, new Object[]{id}, rowMapper)
                .stream()
                .findAny()
                .orElse(null);
    }
}

