package com.szczwany.calculator.Calculation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.szczwany.calculator.Project.model.Project;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calculations")
@JsonIgnoreProperties(value = "updatedAt", allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Calculation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank(message = "You have to specify mathematical expression")
    @Length(max = 100, message = "Maximum of 100 signs")
    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private Double result;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Calculation()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public Double getResult()
    {
        return result;
    }

    public void setResult(Double result)
    {
        this.result = result;
    }

    public Date getLastUpdate()
    {
        return updatedAt;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }
}
