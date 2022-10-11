package exam.todo.dto;

import exam.todo.entity.Importance;
import exam.todo.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateToDoCommand {

    @NotNull(message = "The description cannot be empty")
    private String description;

    @Future(message = "The deadline can only be in the future.")
    private LocalDate deadline;

    private Importance importance;

    private final Status status=Status.NOT_STARTED;

}
