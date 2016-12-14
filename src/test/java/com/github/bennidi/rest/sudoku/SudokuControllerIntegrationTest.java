package com.github.bennidi.rest.sudoku;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SudokuControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.baseUrl = "http://localhost:" + port + "/sudoku";
    }

    @Test
    public void getUnknownBoard() throws Exception {
        ResponseEntity<String> response = template.getForEntity(baseUrl + "/boards/unknown", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getBoardOne() throws Exception {
        ResponseEntity<String> response = template.getForEntity(baseUrl + "/boards/board-one", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
