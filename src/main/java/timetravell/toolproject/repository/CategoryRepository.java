package timetravell.toolproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import timetravell.toolproject.domain.Category;
import timetravell.toolproject.domain.Project;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByCategoryName (String id);

    Category findByProjectIdentifier(String Identifier);

//    Category findByCategoryId( Long categoryId);


//    List<Category> findByCategoryId(Long categoryId);

    Category findByDescriptionCategory (String descriptionCategory);

    @Override
    Iterable<Category> findAll();

//    Category findByProjectIdentifie (String Identifier);

//    @Query("select d.projects from Category d where d.id = ?1")
//    List<Project> getProjectsByCategory(Long id);
}
