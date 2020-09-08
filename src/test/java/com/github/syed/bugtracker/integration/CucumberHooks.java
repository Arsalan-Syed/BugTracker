package com.github.syed.bugtracker.integration;

import com.github.syed.bugtracker.user.UserRepository;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

public class CucumberHooks {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;


    @After
    public void afterScenario(){
        clearAllDatabaseRows();
        DataStorage.clear();
    }

    private void clearAllDatabaseRows() {
        List<String> tableNames = findAllTableNames().stream().filter(tableName -> !tableName.equals("USER")).collect(Collectors.toList());
        tableNames.forEach(tableName -> jdbcTemplate.execute("DELETE FROM "+tableName));
    }

    private List<String> findAllTableNames() {
        return jdbcTemplate.query("SHOW TABLES",
                    (resultSet, i) -> resultSet.getString("TABLE_NAME"));
    }

}