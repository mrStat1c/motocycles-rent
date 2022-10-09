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

    public Motocycle get(int id) {
        String sql = "SELECT * FROM motocycle WHERE id=?";
        return this.jdbcTemplate.query(sql, new Object[]{id}, this.rowMapper)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void add(Motocycle motocycle) {
        String sql = "INSERT INTO motocycle (model, vin, `release`, weight, power, type) VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(
                sql,
                motocycle.getModel(),
                motocycle.getVin(),
                motocycle.getReleaseYear(),
                motocycle.getWeight(),
                motocycle.getPower(),
                motocycle.getType()
        );
    }

    public void update(Motocycle motocycle) {
        String sql = "UPDATE motocycle SET model = ?, vin = ?, `release` = ?, weight = ?, power = ?, type = ? WHERE id = ?";
        this.jdbcTemplate.update(
                sql,
                motocycle.getModel(),
                motocycle.getVin(),
                motocycle.getReleaseYear(),
                motocycle.getWeight(),
                motocycle.getPower(),
                motocycle.getType(),
                motocycle.getId()
        );
    }

    public void delete(int motoId) {
        String sql = "DELETE FROM motocycle WHERE id = ?";
        this.jdbcTemplate.update(sql, motoId);
    }

}
