package exam.todo.repository;

import exam.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository  extends JpaRepository<ToDo, Long> {
    List<ToDo> findToDosByUserId(long userId);
}
