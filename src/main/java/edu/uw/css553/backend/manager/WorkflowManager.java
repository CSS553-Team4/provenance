package edu.uw.css553.backend.manager;

import edu.uw.css553.backend.entities.Workflow;

import java.util.List;

public interface WorkflowManager {

    /**
     * Responsible for saving a workflow to the data store
     */
    public void saveWorkflow( Workflow workflow );

    /**
     * Responsible for retrieving all workflows from the data store
     */
    public List<Workflow> listAvailableWorkflows();

}