package org.qaddict.sqad;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.qaddict.sqad.audit.RevisionFactory;
import org.qaddict.sqad.data.Record;

import static java.time.ZonedDateTime.now;

public class AuditListener {

    @PrePersist
    public void onCreate(Record<?> aRecord) {
        onUpdate(aRecord);
        aRecord.setCreatedAt(aRecord.getModifiedAt()).setCreatedBy(aRecord.getModifiedBy());
    }

    @PreUpdate
    public void onUpdate(Record<?> aRecord) {
        aRecord.setModifiedAt(now()).setModifiedBy("");
    }

    @PostUpdate
    @PostPersist
    @PostRemove
    public void auditRecord(Record<?> aRecord) {
        Object revision = RevisionFactory.revisionOf(aRecord);
    }

}
