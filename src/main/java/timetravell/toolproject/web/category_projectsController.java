package timetravell.toolproject.web;

import javassist.NotFoundException;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import timetravell.toolproject.domain.Project;
import timetravell.toolproject.domain.category_projects;
import timetravell.toolproject.repository.ProjjectRepository;
import timetravell.toolproject.repository.category_projectsRepository;
import timetravell.toolproject.services.MapValidationErrorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class category_projectsController {

    @Autowired
    private category_projectsRepository category_projectsrepository;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjjectRepository projjectRepository;


    @GetMapping("/project/{id}/comment")
    public List<category_projects> getCommentByProjectId(@PathVariable Long id) throws NotFoundException{
        if(!projjectRepository.existsById(id)){
            throw new NotFoundException("Project not found!");
        }
        return category_projectsrepository.findByProjectId(id);
    }

//    public ResponseEntity<?> getComm

    @PostMapping("/project/{projectId}/comment")
    public category_projects createComment(@PathVariable Long projectId,
                                 @Valid @RequestBody category_projects category_projects) throws NotFoundException {
        return projjectRepository.findById(projectId)
                .map(project -> {
                    category_projects.setProject((Project) project);
                    return category_projectsrepository.save(category_projects);
                }).orElseThrow(() -> new NotFoundException("PostId " + projectId + " not found"));
    }
}
