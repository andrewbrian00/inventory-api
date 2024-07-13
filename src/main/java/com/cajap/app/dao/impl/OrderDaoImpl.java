package com.cajap.app.dao.impl;

import com.cajap.app.dao.OrderDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
