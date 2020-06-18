package timetravell.toolproject.repository;

import org.springframework.data.repository.CrudRepository;
import timetravell.toolproject.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long id);
}
