package org.qaddict.sqad.data.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.qaddict.sqad.data.Record;
import org.qaddict.sqad.data.links.Evidence;

import java.util.Set;

@Entity
public class Issue extends Record<Issue> {

    @Id
    private Long id;

    @ManyToOne
    private Project project;

    private String summary;

    @Column(length = 4096)
    private String description;

    @OneToMany
    @JsonIgnoreProperties("issue")
    private Set<Evidence> evidence;

    public String getSummary() {
        return summary;
    }

    public Issue setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Evidence> getEvidence() {
        return evidence;
    }

    public Issue setEvidence(Set<Evidence> evidence) {
        this.evidence = evidence;
        return this;
    }
}
