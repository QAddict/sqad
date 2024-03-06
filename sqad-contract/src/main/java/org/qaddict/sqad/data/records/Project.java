package org.qaddict.sqad.data.records;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import org.qaddict.sqad.AuditListener;
import org.qaddict.sqad.data.Record;

@Entity
@EntityListeners(AuditListener.class)
public class Project extends Record<Project> {

    @Id
    private Long id;

    private String name;

    @Column(length = 4096)
    private String description;

    private String homePage;

    private String icon;


    private int leftBoundary;

    private int rightBoundary;

    private int depth;


    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHomePage() {
        return homePage;
    }

    public Project setHomePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Project setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public int getLeftBoundary() {
        return leftBoundary;
    }

    public Project setLeftBoundary(int leftBoundary) {
        this.leftBoundary = leftBoundary;
        return this;
    }

    public int getRightBoundary() {
        return rightBoundary;
    }

    public Project setRightBoundary(int rightBoundary) {
        this.rightBoundary = rightBoundary;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public Project setDepth(int depth) {
        this.depth = depth;
        return this;
    }

}
