package exam.todo.service;

import exam.todo.dto.*;


import exam.todo.entity.ToDo;
import exam.todo.repository.ToDoRepository;
import exam.todo.repository.UserNotFoundException;

import exam.todo.repository.UsersRepository;
import liquibase.repackaged.org.apache.commons.collections4.comparators.ReverseComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class UserService {

    private UsersRepository usersRepository;

    private ToDoRepository toDoRepository;

    private UserMapper userMapper;

    public UserDto createToDo(CreateUserCommand command) {
        var user = userMapper.toEntity(command);
        usersRepository.save(user);
        return userMapper.toDto(user);
    }

    public ToDoDto createToDo(long userId, CreateToDoCommand createToDoCommand){

        var toDo=userMapper.toEntity(createToDoCommand);
        var user=usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + userId));;

        toDo.setUser(user);
        toDoRepository.save(toDo);
        return userMapper.toDto(toDo);
    }


    public List<ToDoDto>  listToDos(long id, Optional<String> status){

      var user = usersRepository.findUserWithToDos(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));

        List<ToDo> toDos;

      if(status.isEmpty()){

            toDos=user.getToDoList()
                   .stream()
                   .sorted(Comparator.comparingInt(d -> ((ToDo)d).getImportance().getValue()).reversed())
                   .toList();
      }
       else{

             toDos= user.getToDoList()
                     .stream()
                     .filter(toDo->toDo.getStatus().toString().equals(status.get().toUpperCase()))
                     .sorted(Comparator.comparingInt(d -> ((ToDo)d).getImportance().getValue()).reversed())
                     .toList();

      }
        return  userMapper.toToDoDto(toDos);


    }




    @Transactional
    public ToDoDto updateToDo(long id, UpdateUserCommand command) {
        var toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));

        toDo.setImportance(command.getImportance());

        return userMapper.toDto(toDo);
    }


}
