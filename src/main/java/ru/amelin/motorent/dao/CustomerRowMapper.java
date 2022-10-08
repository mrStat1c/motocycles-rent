package ru.amelin.motorent.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.amelin.motorent.models.Customer;
import ru.amelin.motorent.utils.RowMapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setAge(RowMapperUtil.getIntValue(rs, "age"));
        customer.setDriveExperience(RowMapperUtil.getIntValue(rs, "driver_exp"));
        customer.setDriverLicenseNumber(rs.getString("driver_lic"));
        customer.setEmail(rs.getString("email"));
        customer.setPhoneNumber(rs.getString("phone"));
        return customer;
    }


}
