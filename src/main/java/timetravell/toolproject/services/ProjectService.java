package timetravell.toolproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timetravell.toolproject.domain.Category;
import timetravell.toolproject.domain.Project;
import timetravell.toolproject.domain.User;
import timetravell.toolproject.exceptions.BusinessException;
import timetravell.toolproject.exceptions.ProjectIdException;
import timetravell.toolproject.exceptions.ProjectNotFoundException;
import timetravell.toolproject.repository.CategoryRepository;
import timetravell.toolproject.repository.ProjjectRepository;
import timetravell.toolproject.repository.UserRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjjectRepository projjectRepository;

    private final CategoryRepository categoryRepository;

    private Project project;

    @Autowired
    private UserRepository userRepository;

    public ProjectService(CategoryRepository categoryRepository, ProjjectRepository projjectRepository) {
        this.categoryRepository = categoryRepository;
        this.projjectRepository = projjectRepository;
    }

    public Project saveOrUpdateProject(Project project, String username){
        try{
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projjectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projjectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projjectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectid){
        Project project = projjectRepository.findByProjectIdentifier(projectid.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Cannot delete Project with ID'"+projectid+"'.This project does not exist");
        }

        projjectRepository.delete(project);
    }

//    public void deleteProjectCategory(String categoryId, String projectId){
//        Project project = findProjectCategory(categoryId,projectId);
//        projjectRepository.delete(project);
//    }

    public Project findProjectByCategory (long categoryId, long projectId, String username){
        Category category = categoryRepository.findById(categoryId).get();

//          if(!project.getProjectIdentifier().equals(categoryId)) {
//              throw new ProjectNotFoundException("Project '"+projectId+"' does not exist in project: '"+categoryId);
//           }

        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account");
        }

        return (Project) projjectRepository.findById(projectId).get();
    }

    //before modify delete such as findProjectByCategory to search by categoryId and projectId not with username
    //now is set with categoryId, projectId and username

    public void deleteProject(long categoryId, long projectId, String username){
        Project project = findProjectByCategory(categoryId, projectId, username);

        projjectRepository.delete(project);
    }

//    public Project findProjectById(Integer projectId) throws BusinessException {
//        Project project=query_repository
//    }
}
