package exam.todo.service;

import exam.todo.dto.CreateToDoCommand;
import exam.todo.dto.CreateUserCommand;
import exam.todo.dto.ToDoDto;
import exam.todo.dto.UserDto;
import exam.todo.entity.ToDo;
import exam.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    User toEntity(CreateUserCommand command);

    ToDo toEntity(CreateToDoCommand command);

    UserDto toDto(User user);

    ToDoDto toDto(ToDo toDo);

    List<ToDoDto> toToDoDto(List<ToDo> toDos);



}
