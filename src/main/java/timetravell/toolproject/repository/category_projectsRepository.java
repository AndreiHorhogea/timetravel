package timetravell.toolproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import timetravell.toolproject.domain.category_projects;

import java.util.List;
import java.util.Optional;

public interface category_projectsRepository extends CrudRepository<category_projects,String> {

    @Override
    Iterable<category_projects> findAll();

    List<category_projects> findByProjectId(Long projectId);

//    Optional<Object> findById(Long comment_id);
}
