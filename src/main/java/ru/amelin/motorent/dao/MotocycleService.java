package ru.amelin.motorent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.amelin.motorent.models.Motocycle;

import java.util.List;

@Component
public class MotocycleService {

    private final JdbcTemplate jdbcTemplate;
    private final MotocycleRowMapper rowMapper;

    @Autowired
    public MotocycleService(JdbcTemplate jdbcTemplate, MotocycleRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Motocycle> getAll() {
        String sql = "SELECT * FROM motocycle";
        return this.jdbcTemplate.query(sql, this.rowMapper);
    }

}
