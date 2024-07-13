package com.cajap.app.dao.impl;

import com.cajap.app.dao.InventoryDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class InventoryDaoImpl implements InventoryDao {

    private final JdbcTemplate jdbcTemplate;

    public InventoryDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
