package edu.uw.css553.backend.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * An action implementation that runs a tool with the given input and
 * returns the object result.
 * <p/>
 * Usage:
 * Action action = new Action();
 * <p/>
 * Map<String, Object> params = new HashMap<>();
 * params.put("scriptText", "input.toUpperCase()");
 * action.setParameters(params);
 * <p/>
 * Object result = action.execute("hello world!");
 * <p/>
 * assertEquals(result, "HELLO WORLD!");
 */
@Entity
public abstract class Action implements ActionInterface, Serializable {

    @Basic
    protected String name;
    @Basic
    protected String description;
    @Basic
    protected int sequence;
    @Basic
    protected String workflowId;
    @Basic
    protected String toolURL;
    @Basic
    protected String outputURL;
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
    @OneToMany
    protected List<WorkflowParameter> params;

    /**
     * Evaluates the command and returns the result. The input is
     * bound to a variable named "input" in the script.
     *
     * @param input
     * @return
     */
    @Override
    public Object execute(Object input) {
        // pass-through for non-specific action. Specific action must be implemented in the subclass.
        return input;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getSequence() {
        return sequence;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public String getToolURL() {
        return toolURL;
    }

    public String getOutputURL() {
        return outputURL;
    }

    public List<WorkflowParameter> getParameters() {
        return params;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public void setToolURL(String toolURL) {
        this.toolURL = toolURL;
    }

    public void setOutputURL(String outputURL) {
        this.outputURL = outputURL;
    }

    /**
     * Set the script parameters. The only key recognized is 'scriptText' which
     * should contain the actual text of the script to run. All other keys are
     * ignored.
     *
     * @param params A map of the parameters
     */
    @Override
    public void setParameters(List<WorkflowParameter> params) {
        this.params = params;
    }
    
    public abstract Action createAction();

}