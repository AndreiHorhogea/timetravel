package timetravell.toolproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category_projects")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class category_projects {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Please enter id")
    private Long comment_id;

    //    @Column( name = "text", updatable = true, nullable = false )
    private String text;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id",nullable = false)
    @JsonIgnore
    private Project project;

    public category_projects() {
    }

    public category_projects(Long comment_id, String text){
        this.comment_id = comment_id;
        this.text = text;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public String getText() {
        return text;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Project getProject() { return project; }

//    public void setProject(Project proj) {
//        this.project = project;
//    }

    public void setProject(Project project) {
        this.project = project;
    }

//    public void setProject(Object project) {
//    }
}
