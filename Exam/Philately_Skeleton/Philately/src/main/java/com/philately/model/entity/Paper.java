package com.philately.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "papers")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PaperType paperName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private Set<Stamp> stamps = new HashSet<>();

    public Paper(PaperType paperName, String description) {
        this.paperName = paperName;
        this.description = description;
    }

    public Paper() {
    }

    public String getName() {
        return name;
    }

    public Paper setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaperType getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperType paperName) {
        this.paperName = paperName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(Set<Stamp> stamps) {
        this.stamps = stamps;
    }
}
