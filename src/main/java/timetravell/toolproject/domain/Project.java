package timetravell.toolproject.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull(message = "Please enter id")
//    @Column(name = "id")
//    @Column(name="id" , unique = true, nullable = false)
    private Long id;
    @NotBlank(message = "Project name is required")
    @Column(name = "project_name")
    private String projectName;
    @NotBlank(message = "Project Identifier is required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Project description is required")
    @Column(name = "description")
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    @NotBlank(message = "Project description is required")
    @Column(name = "descriere")
    private String descriere;

    @Column(updatable = false, unique = true)
    private String categoryName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date create_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date update_At;

//    @Column(updatable = false)
//    private String categoryName;


    @ManyToOne(fetch = FetchType.EAGER,optional = false) //REMOVE REFRESH
    @JoinColumn(name = "category_id",nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String projectLeader;


    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<category_projects> category_projects = new HashSet<>();

    public Project(){}

    public Project(Long id,String projectName, String projectIdentifier, String descriere){
        this.id = id;
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.descriere = descriere;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    //    public String getcategoryName() {
//        return categoryName;
//    }
//
//    public void setcategoryName(String descriptionCategory) {
//        this.categoryName = descriptionCategory;
//    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategory_Name() {
        return categoryName;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
//        category.getProjects().add(this);
    }

//    @Override
//    public String toString(){
//        return "Project{"+
//                "id=" +id+
//                ", projectIdentifier='" + projectIdentifier + '\'' +
//                ", projectName='" + projectName + '\'' +
//                ", description='" + description + '\'' +
//                ", category='" + category +
//                '}';
//    }

//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
}
