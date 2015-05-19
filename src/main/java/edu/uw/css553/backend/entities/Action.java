package edu.uw.css553.backend.entities;

import java.util.Map;

public interface Action {

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
    public void setParameters( Map<String, Object> params );

    /**
     * @return The display name of the action.
     */
    public String getName();

}