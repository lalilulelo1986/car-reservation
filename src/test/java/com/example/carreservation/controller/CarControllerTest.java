package com.example.carreservation.controller;

import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {
    @LocalServerPort
    private Integer port;

    @Test
    void findById() {
        // given
        RestTemplate restTemplate = new RestTemplate();

        // when
        ResponseEntity<CarDto> createResponse = restTemplate.
                postForEntity("http://localhost:" + port + "/api/v1/car", new CarCreateDto("audi", "model"), CarDto.class);
        assertTrue(createResponse.getStatusCode().is2xxSuccessful());
        CarDto body = createResponse.getBody();
        ResponseEntity<CarDto> getResponse = restTemplate.
                getForEntity("http://localhost:" + port + "/api/v1/car/" + body.getId(), CarDto.class);

        // then
        assertEquals("audi", getResponse.getBody().getMake());
        assertEquals("model", getResponse.getBody().getModel());
    }

    @Test
    void findCarReservations() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }
}