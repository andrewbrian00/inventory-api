package com.cajap.app.dao.impl;

import com.cajap.app.dao.SupplierDao;
import com.cajap.app.domain.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierDaoImpl implements SupplierDao {

    private final JdbcTemplate jdbcTemplate;

    public SupplierDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Supplier supplier) {
        jdbcTemplate.update(
                "INSERT INTO suppliers " +
                        "(name," +
                        "address)" +
                        "VALUES (?,?)",
                supplier.getName(),
                supplier.getAddress()
        );
    }

    @Override
    public Optional<Supplier> findOne(long supplierId) {
        List<Supplier> results = jdbcTemplate.query(
                "SELECT id, name, address " +
                        "FROM suppliers WHERE id = ? LIMIT 1",
                new SupplierDaoImpl.SupplierRowMapper(), supplierId);
        return results.stream().findFirst();
    }

    public static class SupplierRowMapper implements RowMapper<Supplier> {

        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Supplier.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
        }
    }

}
