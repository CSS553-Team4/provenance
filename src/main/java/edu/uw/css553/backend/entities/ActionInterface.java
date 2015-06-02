package edu.uw.css553.backend.entities;

import java.util.List;

public interface ActionInterface extends EntityInterface {

    /**
     * Runs the action, using the provided Data object as input
     * and returns its modified form. Provided Map of parameters
     * will dictate how the execution of the Action is performed.
     */
    public Object execute( Object input );  // Need type check on input data type

    /**
     * Set the parameters to modify the action's execution.
     * @param params A map of the parameters
     */
    public void setParameters( List<WorkflowParameter> params );

    /**
     * @return The display name of the action.
     */
    public String getName();

}