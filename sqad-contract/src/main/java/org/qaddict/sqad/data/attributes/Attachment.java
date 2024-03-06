package org.qaddict.sqad.data.attributes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private ZonedDateTime time;

    private String uri;

    public Long getId() {
        return id;
    }

    public Attachment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Attachment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Attachment setTime(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Attachment setUri(String uri) {
        this.uri = uri;
        return this;
    }

}
