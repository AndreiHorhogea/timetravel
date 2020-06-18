package timetravell.toolproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timetravell.toolproject.domain.Category;
import timetravell.toolproject.domain.Project;
import timetravell.toolproject.exceptions.BusinessException;
import timetravell.toolproject.exceptions.CategoryIdException;
import timetravell.toolproject.repository.CategoryRepository;

import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private final ProjectService projectService;

    public CategoryService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Category saveOrUpdateCategory(Category category){
        try{
            category.setCategoryName(category.getCategoryName().toUpperCase());
            return categoryRepository.save(category);
        }catch (Exception e){
            throw new CategoryIdException("Category ID '"+category.getCategoryName().toUpperCase()+"' already exists");
        }
    }


    public Category findByCategory(String categoryId){
        Category category = categoryRepository.findByCategoryName(categoryId.toUpperCase());

        if(category == null){
            throw new CategoryIdException("Category '"+categoryId+"' does not exist");
        }
        return category;
    }

    public Category addCategories(Category category ){
        categoryRepository.save(category);
        return category;
    }


    public Iterable<Category> findAll (){
        return categoryRepository.findAll();
    }

    public Category deleteAProjectFromExistingCategory(String categoryId, String projectId) throws BusinessException {
        Category categoryToBeUpdated = findByCategory(categoryId);

        Project projectToBeDeleted = projectService.findProjectByIdentifier(projectId);

        Set<Project> allProjects = categoryToBeUpdated.getProjects();
        allProjects.remove(projectToBeDeleted);

        return categoryToBeUpdated;

    }

//    public List<Project> getProjectsByCategory(Long id){
////        return categoryRepository.geCategory(id);
//    }
}
