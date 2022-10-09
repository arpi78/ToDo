package exam.todo.service;

import exam.todo.dto.*;


import exam.todo.repository.ToDoRepository;
import exam.todo.repository.UserNotFoundException;

import exam.todo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UsersRepository usersRepository;

    private ToDoRepository toDoRepository;

    private UserMapper userMapper;

    public UserDto createUser(CreateUserCommand command) {
        var user = userMapper.toEntity(command);
        usersRepository.save(user);
        return userMapper.toDto(user);
    }

    public ToDoDto createUser(long userId, CreateToDoCommand createToDoCommand){

        var toDo=userMapper.toEntity(createToDoCommand);
        var user=usersRepository.getReferenceById(userId);
        toDo.setUser(user);
        toDoRepository.save(toDo);
        return userMapper.toDto(toDo);
    }


    public List<ToDoDto>  listToDos(long id){

      var user = usersRepository.findUserWithToDos(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));

       return  userMapper.toToDoDto(user.getToDoList());

    }




    @Transactional
    public ToDoDto updateToDo(long id, UpdateUserCommand command) {
        var toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));

        toDo.setImportance(command.getImportance());

        return userMapper.toDto(toDo);
    }


}
