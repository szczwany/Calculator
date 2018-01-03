package com.szczwany.calculator.Calculation.model;

import com.fasterxml.jackson.annotation.*;
import com.szczwany.calculator.Project.model.Project;
import com.szczwany.calculator.Utils.Globals;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Size(max = 100)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Pattern(regexp = Globals.MATH_EXPRESSION_REGEX)
    @Size(max = 100)
    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private Double result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore()
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

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
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
