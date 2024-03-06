package org.qaddict.sqad.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import org.qaddict.sqad.audit.Revision;
import org.qaddict.sqad.data.attributes.Attachment;
import org.qaddict.sqad.data.attributes.Comment;

import java.time.ZonedDateTime;
import java.util.List;

@MappedSuperclass
public class Record<T extends Record<T>> {

    private String state;

    private String tags;

    @Revision
    private Long revision;

    private String createdBy;

    private ZonedDateTime createdAt;

    private String modifiedBy;

    private ZonedDateTime modifiedAt;

    private String deletedBy;

    private ZonedDateTime deletedAt;

    @OneToMany
    @JsonIgnore
    private List<Attachment> attachments;

    @OneToMany
    @JsonIgnore
    private List<Comment> comments;

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    public String getState() {
        return state;
    }

    public T setState(String state) {
        this.state = state;
        return self();
    }

    public String getTags() {
        return tags;
    }

    public T setTags(String tags) {
        this.tags = tags;
        return self();
    }

    public Long getRevision() {
        return revision;
    }

    public T setRevision(Long revision) {
        this.revision = revision;
        return self();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public T setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return self();
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public T setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return self();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public T setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return self();
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public T setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return self();
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public T setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
        return self();
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public T setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return self();
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Record<T> setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Record<T> setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
