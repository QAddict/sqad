package org.qaddict.sqad.data.links;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.records.Issue;
import org.qaddict.sqad.data.records.Run;

@Entity
public class Evidence {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Issue issue;

    @ManyToOne
    private Run run;

    private Type type = Type.CAUSE;

    public Long getId() {
        return id;
    }

    public Evidence setId(Long id) {
        this.id = id;
        return this;
    }

    public Issue getIssue() {
        return issue;
    }

    public Evidence setIssue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public Run getRun() {
        return run;
    }

    public Evidence setRun(Run run) {
        this.run = run;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Evidence setType(Type type) {
        this.type = type;
        return this;
    }

    public enum Type {
        CAUSE, TIME, SPACE, PATTERN
    }

}
