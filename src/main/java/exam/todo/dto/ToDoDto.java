package exam.todo.dto;


import exam.todo.entity.Importance;
import exam.todo.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ToDoDto {

    private String description;

    private LocalDate deadline;

    private Importance importance;

    private Status status;
}
