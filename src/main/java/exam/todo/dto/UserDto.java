package exam.todo.dto;

import exam.todo.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String userEmail;

    private List<ToDo> toDoList;
}
