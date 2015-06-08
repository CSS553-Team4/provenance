package edu.uw.css553.backend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Christina Burnett
 * @version 1.0 5/30/2015
 *
 * The ExecutionLog class implements an EntityInterface to store execution log header information in the data store.
 * It includes a list of LogStep instances that are records of the work performed from the referenced workflow.
 */
@Entity
public class ExecutionLog implements EntityInterface {
    @Id
    public String id;
    @Basic
    private String workflowId;
    @Basic
    private String inputURL;
    @Basic
    private Timestamp executionStartTime;
    @Basic
    private Timestamp executionEndTime;
    @Basic
    private String returnValue;
    @Basic
    private String returnMessage;
    @Basic
    private String executionNotes;
    @Basic
    private int stepCount;
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
    private ArrayList<LogStep> actions;

    @Override
    public String getId() {
        return id;
    }

    public String getInputURL() {
        return inputURL;
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

    public String getExecutionNotes() {
        return executionNotes;
    }

    public int getStepCount() {
        return stepCount;
    }

    public String getWorkflowId() {
        return workflowId;
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

    public ArrayList<LogStep> getActions() {
        return actions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public void setInputURL(String inputURL) {
        this.inputURL = inputURL;
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

    public void setExecutionNotes(String executionNotes) {
        this.executionNotes = executionNotes;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
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

    public void setActions(ArrayList<LogStep> actions) {
        this.actions = actions;
    }

}
