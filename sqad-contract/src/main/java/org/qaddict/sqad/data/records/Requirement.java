package org.qaddict.sqad.data.records;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

@Entity
public class Requirement extends Record<Requirement> {

    @Id
    private Long id;

    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public Requirement setId(Long id) {
        this.id = id;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Requirement setProject(Project project) {
        this.project = project;
        return this;
    }
}
