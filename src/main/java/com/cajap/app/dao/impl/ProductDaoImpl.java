package com.cajap.app.dao.impl;

import com.cajap.app.dao.ProductDao;
import com.cajap.app.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Product product) {
        jdbcTemplate.update(
                "INSERT INTO cajap.products (part_number, name, amount, unit_measure, description, category) " +
                        "VALUES (?,?,?,?,?,?)",
                product.getPartNumber(),
                product.getName(),
                product.getAmount(),
                product.getUnitMeasure(),
                product.getDescription(),
                product.getCategory()
        );

    }

    @Override
    public Optional<Product> findOne(long productId) {
        List<Product> results = jdbcTemplate.query(
                "SELECT id, part_number, name, amount, unit_measure, description, category " +
                        "FROM cajap.products WHERE id = ? LIMIT 1",
                new ProductRowMapper(), productId);
        return results.stream().findFirst();
    }

    public static class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getLong("id"))
                    .partNumber(rs.getString("part_number"))
                    .name(rs.getString("name"))
                    .amount(rs.getDouble("amount"))
                    .unitMeasure(rs.getString("unit_measure"))
                    .description(rs.getString("description"))
                    .category(rs.getString("category"))
                    .build();
        }
    }

    @Override
    public List<Product> find() {
        return jdbcTemplate.query(
                "SELECT id, part_number, name, amount, unit_measure, description, category" +
                        " FROM cajap.products ",
                new ProductRowMapper()
        );
    }

    @Override
    public void truncate() {
        jdbcTemplate.execute("DELETE FROM products");
    }
}