package org.qaddict.sqad.data.records;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

@Entity
public class Test extends Record<Test> {

    @Id
    private Long id;

    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public Test setId(Long id) {
        this.id = id;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Test setProject(Project project) {
        this.project = project;
        return this;
    }
}
