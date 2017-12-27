package com.szczwany.calculator.Project.model;

import com.szczwany.calculator.Calculation.model.Calculation;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    //@JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Calculation> calculations = new ArrayList<>();

    public Project()
    {
    }

    public Project(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Calculation> getCalculations()
    {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations)
    {
        this.calculations = calculations;
    }
}
