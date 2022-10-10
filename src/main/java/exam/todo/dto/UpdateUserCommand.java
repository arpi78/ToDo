package exam.todo.dto;

import exam.todo.entity.Importance;
import exam.todo.entity.Status;
import lombok.Data;

@Data
public class UpdateUserCommand {

    private Status status;

}
