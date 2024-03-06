package org.qaddict.sqad.data.links;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.qaddict.sqad.data.records.Requirement;
import org.qaddict.sqad.data.records.Test;

@Entity
public class Coverage {

    @Id
    private Long requirementId;

    @Id
    private Long testId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "requirementId")
    private Requirement requirement;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "testId")
    private Test test;

    public Requirement getRequirement() {
        return requirement;
    }

    public Coverage setRequirement(Requirement requirement) {
        this.requirement = requirement;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public Coverage setTest(Test test) {
        this.test = test;
        return this;
    }

}
