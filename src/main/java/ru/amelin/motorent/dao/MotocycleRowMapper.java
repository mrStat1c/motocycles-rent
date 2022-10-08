package ru.amelin.motorent.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.amelin.motorent.models.Motocycle;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MotocycleRowMapper implements RowMapper<Motocycle> {

    @Override
    public Motocycle mapRow(ResultSet rs, int rowNum) throws SQLException {

        Motocycle motocycle = new Motocycle();
        motocycle.setId(rs.getInt("id"));
        motocycle.setModel(rs.getString("model"));
        motocycle.setVin(rs.getString("vin"));
        motocycle.setReleaseYear(rs.getInt("release"));
        motocycle.setWeight(rs.getInt("weight"));
        motocycle.setPower(rs.getInt("power"));
        motocycle.setType(rs.getString("type"));
        return motocycle;
    }
}
