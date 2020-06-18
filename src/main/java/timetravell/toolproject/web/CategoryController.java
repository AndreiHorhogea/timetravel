package timetravell.toolproject.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import timetravell.toolproject.domain.Category;
import timetravell.toolproject.domain.Project;
import timetravell.toolproject.exceptions.BusinessException;
import timetravell.toolproject.exceptions.NotFoundException;
import timetravell.toolproject.repository.CategoryRepository;
import timetravell.toolproject.services.CategoryService;
import timetravell.toolproject.services.MapValidationErrorService;
import timetravell.toolproject.services.ProjectService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/category/all")
    public Iterable<Category> getAllCategory(){
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id){
        Category category = categoryService.findByCategory(id);

        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

//    @GetMapping("/category/{id}/{projectid}")
//    public ResponseEntity<?> getProjectTask(@PathVariable String id, @PathVariable String projectid){
//        Project project = projectService.findPTByProjectSequence(id, projectid);
//        return new ResponseEntity<Project>( project, HttpStatus.OK);
//    }

    @PostMapping("/category")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Category category1 = categoryService.saveOrUpdateCategory(category);
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @Valid @RequestBody Category categoryUpdated){
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setCategoryName(categoryUpdated.getCategoryName());
                    category.setDescriptionCategory(categoryUpdated.getDescriptionCategory());
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new NotFoundException("Category not found with id " + id));
    }

    @DeleteMapping("/project/category/{categoryId}/{projectId}")
    public ResponseEntity<Category> deleteProjectFromExistingCategory(@PathVariable String categoryId, @PathVariable String projectId){
        try{
            Category category = categoryService.deleteAProjectFromExistingCategory(categoryId, projectId);
            return ResponseEntity.ok(category);
        } catch (BusinessException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/category/{categoryId}/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable long categoryId, @PathVariable long projectId, Principal principal) {
        projectService.deleteProject(categoryId, projectId, principal.getName());

        return new ResponseEntity<>("Project " + projectId + " was deleted successfully", HttpStatus.OK);
    }

//    @PostMapping("/category/project/{id}")
//    public ResponseEntity<?> addProjecttoCategory(@Valid @RequestBody Project project, BindingResult result, @PathVariable String id){
//
//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
//        if (errorMap != null) return errorMap;
//
//        Project project1 = projectService.addProject(id, project);
//
//        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
//    }
}
