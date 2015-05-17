package edu.uw.css553.backend.entities;

import java.util.List;

/**
 * WorkflowInterface responsible for providing a layout, though not an implemen-
 * tion of the workflow object itself
 * @author Zulqurnain Hussain
 */
public interface Workflow {
    /**
     * Add an action to the workflow
     * @param action : an action to be added to the workflow
     */
    public void addAction(Action action);
    /**
     * Remove a specified action from the workflow, given an action
     * @param action : an action object to be removed from the workflow actions
     */
    public void removeAction(Action action) throws WorkflowInstance.NoSuchActionException;
    /**
     * Get a list of the actions that are part of the workflow
     * @return a list of all the action elements in workflow
     */
    public List<Action> getActions();
    /**
     * Set a name for the workflow
     * @param name : the name for the workflow
     */
    public void setName(String name);
    /**
     * Get the name of the workflow
     * @return String name of the workflow object
     */
    public String getName();
    /**
     * Allows for the initial specification of the input that would go into
     * a workflow
     * @param input : input can be user input or a specified file
     * @param isFile : flag for determining if the input is a file or not
     */
    public void setWorkflowInput(String input, boolean isFile);
}
