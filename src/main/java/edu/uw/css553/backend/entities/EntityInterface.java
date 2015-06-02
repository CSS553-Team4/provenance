package edu.uw.css553.backend.entities;

import java.sql.Timestamp;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created by clbur_000 on 5/25/2015.
 */
public interface EntityInterface {
    public abstract String getId();

    public abstract String getCreatedBy();

    public abstract Timestamp getCreationTime();

    public abstract String getModifiedBy();

    public abstract Timestamp getModificationTime();

    public abstract int getVersion();
}
