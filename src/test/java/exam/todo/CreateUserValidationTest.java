package exam.todo;


import exam.todo.dto.CreateUserCommand;
import exam.todo.entity.User;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class CreateUserValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }

    @Test
    public void testWrongEmail() {
        CreateUserCommand userCommand = new CreateUserCommand();
        userCommand.setUserEmail("Jackyahoo.com");
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(userCommand);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    public void testCorrectEmail() {
        CreateUserCommand userCommand = new CreateUserCommand();
        userCommand.setUserEmail("John.Doe@gmail.com");
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(userCommand);
        assertEquals(true, violations.isEmpty());
    }




}
