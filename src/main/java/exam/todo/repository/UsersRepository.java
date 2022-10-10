package exam.todo.repository;

import exam.todo.entity.User;
import exam.todo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {


    @Query("select distinct e from User e left join fetch e.toDoList")
    List<User> findUsersWithToDos();

    @Query("select distinct e from User e left join fetch e.toDoList where e.id = :id")
    Optional<User> findUserWithToDos(long id);

    @Query("from User e where e.id = :id")
    Optional<User> findUserWithId(long id);

}
