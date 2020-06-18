package timetravell.toolproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@Data
//@AllArgsConstructor
//@ToString
@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

    @Id
    @NotNull(message = "Please enter id")
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Category name is required")
    @Column(name = "category_name")
    private String categoryName;
    @NotBlank(message = "Category description is required")
    @Column(name = "description_category")
    private String descriptionCategory;

    private String projectIdentifier;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Set<Project> projects;

    public Category(){}

    public Category(Long id,String categoryName, String descriptionCategory){
        this.id = id;
        this.categoryName=categoryName;
        this.descriptionCategory=descriptionCategory;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Long getId() {
        return id;
    }

    public String getCategoryName() { return categoryName; }

    public Set<Project> getProjects(){
        return this.projects;
    }

    public void setProjects(Set<Project> projects){
        this.projects = projects;
//        for (Project project : projects){
//            project.setCategory(this);
//        }
    }
}
