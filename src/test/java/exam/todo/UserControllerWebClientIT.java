package exam.todo;


import exam.todo.dto.CreateUserCommand;
import exam.todo.dto.UserDto;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerWebClientIT {

    @Autowired
    WebTestClient webClient;

    @Test
    void testCreateUser(){

        var result = webClient
                .post()
                .uri( "/api/users")
                .bodyValue(new CreateUserCommand("johne@gmail.com"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserDto.class).value(e -> assertEquals("johne@gmail.com", e.getUserEmail()))
                .returnResult().getResponseBody();




    }

    @Test
    void testCreateInvalidUser(){
        webClient
                .post()
                .uri("/api/users")
                .bodyValue(new CreateUserCommand("olivia.doe+gmail.com"))
                .exchange()
                .expectStatus().isBadRequest();


    }





}
