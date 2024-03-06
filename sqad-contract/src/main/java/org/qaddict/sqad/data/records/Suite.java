package org.qaddict.sqad.data.records;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

import java.util.Set;

@Entity
public class Suite extends Record<Suite> {

    @Id
    private Long id;

    @ManyToOne
    private Project project;

    private String name;

    @Column(unique = true)
    private String uri;

    @Column(length = 4096)
    private String description;

    @ManyToMany
    private Set<Test> tests;

    public Long getId() {
        return id;
    }

    public Suite setId(Long id) {
        this.id = id;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Suite setProject(Project project) {
        this.project = project;
        return this;
    }

    public String getName() {
        return name;
    }

    public Suite setName(String name) {
        this.name = name;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Suite setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Suite setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public Suite setTests(Set<Test> tests) {
        this.tests = tests;
        return this;
    }
}
