package org.qaddict.sqad.data.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.qaddict.sqad.data.Record;
import org.qaddict.sqad.data.links.Evidence;

import java.util.List;

@Entity
public class Run extends Record<Run> {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Task task;

    @OneToMany
    @JsonIgnoreProperties("run")
    private List<Evidence> evidence;

    public Task getTask() {
        return task;
    }

    public Run setTask(Task task) {
        this.task = task;
        return this;
    }

}
