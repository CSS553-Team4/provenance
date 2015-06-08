package edu.uw.css553.backend.entities;

/**
 * @author Christina Burnett
 * @version 1.0 5/30/2015
 */

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class LogParameter implements EntityInterface {
    @Id
    public String id;
    @Basic
    private String name;

    @Basic
    private String value;

    @Basic
    private int sequence;
    @Basic
    private String createdBy;
    @Basic
    private Timestamp creationTime;
    @Basic
    private String modifiedBy;
    @Basic
    private Timestamp modificationTime;
    @Basic
    private int version;

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public Timestamp getCreationTime() {
        return creationTime;
    }

    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public Timestamp getModificationTime() {
        return modificationTime;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModificationTime(Timestamp modificationTime) {
        this.modificationTime = modificationTime;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
