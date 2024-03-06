package org.qaddict.sqad.data.records;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

@Entity
public class Plan extends Record<Issue> {

    @Id
    private Long id;

    @Column(unique = true)
    private String uri;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Suite suite;

    @ManyToOne
    private Build build;

    public Long getId() {
        return id;
    }

    public Plan setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Plan setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Plan setProject(Project project) {
        this.project = project;
        return this;
    }

    public Suite getSuite() {
        return suite;
    }

    public Plan setSuite(Suite suite) {
        this.suite = suite;
        return this;
    }

    public Build getBuild() {
        return build;
    }

    public Plan setBuild(Build build) {
        this.build = build;
        return this;
    }

}
