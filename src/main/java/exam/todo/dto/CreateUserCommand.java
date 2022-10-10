package exam.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand {


    @Email(message = "A valid email address is required")
    private String userEmail;

}
