package org.qaddict.sqad.data.records;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.qaddict.sqad.data.Record;

@Entity
public class Task extends Record<Task> {

    @Id
    private Long id;

    @Column(unique = true)
    private String uri;

    @ManyToOne
    private Plan plan;

    @ManyToOne
    private Test test;

    public Plan getPlan() {
        return plan;
    }

    public Task setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public Task setTest(Test test) {
        this.test = test;
        return this;
    }

}
