package org.qaddict.sqad.data.attributes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private ZonedDateTime time;

    private String content;

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Comment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Comment setTime(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

}
