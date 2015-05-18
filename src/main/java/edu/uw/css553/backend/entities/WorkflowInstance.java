/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uw.css553.backend.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation instance of the workflow interface
 *
 * @author Zulqurnain Hussain
 */
public class WorkflowInstance implements Workflow {

    public class NoSuchActionException extends Exception {

        public NoSuchActionException(String message) {
            super(message);
        }
    }
    private String name;
    private ArrayList<Action> actions;
    private boolean isFileLoc = false;
    private String worflowInput;
    public boolean isWorkflowOpen = false;
    public int workflowid;

    public WorkflowInstance(String name) {
        this.name = name;
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

    /**
     * Allows for the initial specification of the input that would go into a
     * workflow
     *
     * @param input : input can be user input or a specified file
     * @param isFile : flag for determining if the input is a file or not
     */
    @Override
    public void setWorkflowInput(String input, boolean isFile) {
        this.name = input;
        this.isFileLoc = isFile;
    }

    public boolean isInputFile() {
        return this.isFileLoc;
    }

    public String getWorkflowInput() {
        return this.worflowInput;
    }
}
