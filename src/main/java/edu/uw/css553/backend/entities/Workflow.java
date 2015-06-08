package edu.uw.css553.backend.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * An implementation of the workflow interface
 *
 * @author Zulqurnain Hussain
 */
@Entity
public class Workflow implements WorkflowInterface, Serializable {

    public class NoSuchActionException extends Exception {

        public NoSuchActionException(String message) {
            super(message);
        }
    }
    @Basic
    private String name;
    @OneToMany
    private ArrayList<Action> actions;
    private boolean isFileLoc = false;
    @Basic
    private String workflowInput;
    @Basic
    private String description;
    public boolean isWorkflowOpen = false;
    @Id
    private String id;
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

    public Workflow() {}

    public Workflow(String name) {
        this.name = name;
        this.actions = new ArrayList<Action>();
    }

    /**
     * Add an action to the workflow
     *
     * @param action : an action to be added to the workflow
     */
    @Override
    public void addAction(Action action) {
        this.actions.add(action);
    }
    
    public void addAction(Action action, int index){
        this.actions.add(index, action);
    }
    /**
     * Get a list of the actions that are part of the workflow
     *
     * @return a list of all the action elements in workflow
     */
    @Override
    public List<Action> getActions() {
        return this.actions;
    }

    /**
     * Remove a specified action from the workflow, given an action
     * @exception NoSuchActionException : The action doesnt exist in the workflow
     * @param action : an action object to be removed from the workflow actions
     */
    @Override
    public void removeAction(Action action) throws NoSuchActionException{
        if(!this.actions.contains(action)){
            throw new NoSuchActionException("The specified action doesn't exists");
        }
        else{
            this.actions.remove(action);
        }
    }

    /**
     * Get the name of the workflow
     *
     * @return String name of the workflow object
     */
    @Override
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Allows for the initial specification of the input that would go into a
     * workflow
     *
     * @param input : input can be user input or a specified file
     * @param isFile : flag for determining if the input is a file or not
     */
    @Override
    public void setWorkflowInput(String input, boolean isFile) {
        this.workflowInput = input;
        this.isFileLoc = isFile;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Workflow))
            return false;
        if(obj == this)
            return true;
        Workflow rhs = (Workflow)obj;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }
    
    
    public boolean isInputFile() {
        return this.isFileLoc;
    }

    public String getWorkflowInput() {
        return this.workflowInput;
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setWorkflowInput(String workflowInput) {
        this.workflowInput = workflowInput;
    }

    /**
     * Set a name for the workflow
     *
     * @param name : the name for the workflow
     */
    @Override
    public void setName(String name) {
        if (!name.equals("")) {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

}
