package com.example.daodemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DemoRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String scriptFileName = "selectProductName.sql";
    private final String sql;

    @Autowired
    public DemoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.sql = read(scriptFileName);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<String> getProductName(String name) {
        SqlRowSet resultSet = namedParameterJdbcTemplate.queryForRowSet(sql, new MapSqlParameterSource("name", name));
        List<String> listProducts = new ArrayList<>();
        while (resultSet.next()) {
            String product = resultSet.getString("product_name");
            listProducts.add(product);
        }
        return listProducts;
    }

    public static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
