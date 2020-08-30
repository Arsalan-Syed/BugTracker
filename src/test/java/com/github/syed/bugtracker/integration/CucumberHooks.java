package com.github.syed.bugtracker.integration;

import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CucumberHooks {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @After
    public void afterScenario(){
        clearAllDatabaseRows();
        DataStorage.clear();
    }

    private void clearAllDatabaseRows() {
        List<String> tableNames = findAllTableNames();
        tableNames.forEach(tableName -> jdbcTemplate.execute("DELETE FROM "+tableName));
    }

    private List<String> findAllTableNames() {
        return jdbcTemplate.query("SHOW TABLES",
                    (resultSet, i) -> resultSet.getString("TABLE_NAME"));
    }

}