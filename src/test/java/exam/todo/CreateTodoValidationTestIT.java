package exam.todo;

import exam.todo.dto.CreateToDoCommand;
import exam.todo.dto.CreateUserCommand;
import exam.todo.dto.ToDoDto;
import exam.todo.dto.UserDto;
import exam.todo.entity.Importance;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateTodoValidationTestIT {

    @Autowired
    WebTestClient webClient;

        @Test
        void testCreateToDo(){
            var result = webClient
                    .post()
                    .uri("/api/users/1/todos")
                    .bodyValue(new CreateToDoCommand("Csinálj ebédet", LocalDate.of(2023, Month.AUGUST, 16), Importance.IMPORTANT))
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(ToDoDto.class).value(e -> assertEquals("Csinálj ebédet", e.getDescription()))
                    .returnResult().getResponseBody();
            ;


            assertEquals(LocalDate.of(2023, Month.AUGUST, 16),result.getDeadline());
            assertEquals(Importance.IMPORTANT,result.getImportance());



         webClient
                    .get()
                    .uri("api/users/1/todos")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(ToDoDto.class)
                    .value(toDos -> {
                        System.out.println(toDos);
                        assertTrue(toDos.stream()
                                .anyMatch(e -> e.getDescription().equals("Csinálj ebédet")
                                 && e.getDeadline().equals(LocalDate.of(2023, Month.AUGUST, 16))
                                 && e.getImportance().equals( Importance.IMPORTANT)));});

        }



}
