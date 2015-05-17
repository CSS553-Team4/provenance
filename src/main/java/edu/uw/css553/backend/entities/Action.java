package edu.uw.css553.backend.entities;

public interface Action {

    /**
     * Runs the action, using the provided Data object as input
     * and returns its modified form. Provided Map of parameters
     * will dictate how the execution of the Action is performed.
     */
    public Object execute( Object input );  // Need type check on input data type

    public void setParameters( Map<String, Object> params );

}