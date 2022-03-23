package com.example.daodemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;

@Repository
public class DemoRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String scriptFileName = "selectProductName.sql";

    public String getProductName(String name) {
        String sql = read(scriptFileName) + " :name";
        String product = namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("name", name),
                (resultSet, i) -> new String(resultSet.getString("product_name")));
        return product;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
