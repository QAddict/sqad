package org.qaddict.sqad.data.records;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

@Entity
public class Build extends Record<Build> {

    @Id
    private Long id;

    @Column(unique = true)
    private String uri;

    @ManyToOne
    private Project project;

    private String version;

    public Long getId() {
        return id;
    }

    public Build setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Build setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Build setProject(Project project) {
        this.project = project;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Build setVersion(String version) {
        this.version = version;
        return this;
    }

}
