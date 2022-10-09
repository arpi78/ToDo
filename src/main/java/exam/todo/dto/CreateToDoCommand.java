package exam.todo.dto;

import exam.todo.entity.Importance;
import exam.todo.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateToDoCommand {

    private String description;

    private LocalDate deadline;

    private Importance importance;

    private final Status status=Status.NOT_STARTED;

}
