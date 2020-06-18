package timetravell.toolproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import timetravell.toolproject.domain.Category;
import timetravell.toolproject.domain.Project;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProjjectRepository extends CrudRepository<Project,String> {

    Project findByProjectIdentifier(String projectIdentifier);

    Project findByDescriere(String descriere);

    Project findByCategoryName(String categoryName);

//    Project findByProject (String projectIdentifier);

    @Override
    Iterable<Project> findAll();

    List<Project> findByCategoryId(Long categoryId);

    Page<Project> findAll(Pageable pageable);

    Optional<Object> findById(Long projectId);

    boolean existsById(Long id);

    Project findByProjectName (String projectName);

//    @EntityGraph(attributePaths = "category")
//    List<Project> findFirst10ByOrderByNameAsc();

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Project b WHERE b.category.id = ?1")
//    void deleteInBulkCategoryId(int categoryId);

//    @Transactional
//    void deleteByCategoryId(int categoryId);
}
