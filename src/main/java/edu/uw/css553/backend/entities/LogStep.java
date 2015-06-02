package edu.uw.css553.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import edu.uw.css553.backend.entities.LogParameter;

/**
 * @author Christina Burnett
 * @version 1.0 5/30/2015
 */
@Entity
public class LogStep implements EntityInterface{
    @Id
    private String id;
    @Basic
    private String logId;
    @Basic
    private String name;
    @Basic
    private int sequence;
    @Basic
    private String outputURL;
    @Basic
    private Timestamp executionStartTime;
    @Basic
    private Timestamp executionEndTime;
    @Basic
    private String returnValue;
    @Basic
    private String returnMessage;
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
    @OneToMany
    private ArrayList<LogParameter> params;

    @Override
    public String getId() {
        return id;
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

    public int getSequence() {
        return sequence;
    }

    public String getLogId() {
        return logId;
    }

    public String getName() {
        return name;
    }

    public String getOutputURL() {
        return outputURL;
    }

    public Timestamp getExecutionStartTime() {
        return executionStartTime;
    }

    public Timestamp getExecutionEndTime() {
        return executionEndTime;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public ArrayList<LogParameter> getParams() {
        return params;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOutputURL(String outputURL) {
        this.outputURL = outputURL;
    }

    public void setExecutionStartTime(Timestamp executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public void setExecutionEndTime(Timestamp executionEndTime) {
        this.executionEndTime = executionEndTime;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public void setParams(ArrayList<LogParameter> params) {
        this.params = params;
    }
}
