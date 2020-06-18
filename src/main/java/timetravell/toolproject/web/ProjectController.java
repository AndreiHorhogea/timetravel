package timetravell.toolproject.web;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import timetravell.toolproject.domain.Category;
import org.springframework.web.bind.annotation.*;
import timetravell.toolproject.domain.Project;
import timetravell.toolproject.repository.CategoryRepository;
import timetravell.toolproject.repository.ProjjectRepository;
import timetravell.toolproject.services.MapValidationErrorService;
import timetravell.toolproject.services.ProjectService;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProjjectRepository projjectRepository;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @PostMapping("/{categoryId}/category")
    public Project addProject(@PathVariable Long categoryId, @Valid @RequestBody Project project, Principal principal) throws NotFoundException {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    project.setCategory(category);
                    return projjectRepository.save(project);
                }).orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){

        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){return projectService.findAllProjects();}

    @GetMapping("/{categoryId}")
    public List<Project> getProjectByCategoryId(@PathVariable Long categoryId) throws NotFoundException {
        if (!categoryRepository.existsById(categoryId)){
            throw new NotFoundException("Category or project not found!");
        }
        return projjectRepository.findByCategoryId(categoryId);
    }



    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
    }

//    @DeleteMapping("/project/{categoryId}/{projectId}")
//    public Project deleteProject(@PathVariable Long categoryId, @PathVariable String projectId) throws NotFoundException{
////        Category category = projjectRepository.findByCategoryId(category_id)
//    }

//
//    @DeleteMapping("/category/project/{categoryId}/{projectId}")
//    public ResponseEntity<?> deleteProjectCategory(@PathVariable String categoryId, @PathVariable String projectId){
//        projectService.deleteProjectCategory(categoryId,projectId);
//        return new ResponseEntity<String>("Project Task "+projectId+" was deleted succesfuly", HttpStatus.OK);
//    }
}
