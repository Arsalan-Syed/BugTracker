package com.github.syed.bugtracker;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AbstractIntegrationTest {}