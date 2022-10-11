package exam.todo.controller;


import exam.todo.dto.*;
import exam.todo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/users")
    @Operation(summary = "Create a new user", description = "Create a new user with email address")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserCommand command,
                                                  UriComponentsBuilder uri) {
        var user = userService.createUser(command);
        return ResponseEntity
                .created(uri.path("/api/users/{id}").buildAndExpand(user.getId()).toUri())
                .body(user);



    }


    @PostMapping("/users/{id}/todos")
    @Operation(summary = "Create a new toDo", description = "Create a new toDo with user id")
    public ResponseEntity<ToDoDto> createToDo(@PathVariable("id") long userId,
                                              @Valid @RequestBody CreateToDoCommand command,
                                              UriComponentsBuilder uri){

        var toDo=userService.createToDo(userId,command);

        return ResponseEntity
                .created(uri.path("api/users/{id}/todos").buildAndExpand(userId, toDo.getDescription()).toUri())
                .body(toDo);


    }

    @GetMapping("/users/{id}/todos")
    @Operation(summary = "List toDos", description = "List toDos with user id")
    List<ToDoDto> listToDos(@PathVariable("id") long id,@RequestParam("status") Optional<String> status){

        return userService.listToDos(id,status);
    }

    @PutMapping("/todos/{id}")
    @Operation(summary = "ToDo státuszának a frissítése", description = "A toDo státuszát frirrsíti, a toDo id-jával")
    public ToDoDto updateUser(@PathVariable("id") long id,
                                             @RequestBody UpdateUserCommand command) {
        return userService.updateToDo(id, command);
    }



}
