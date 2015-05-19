package edu.uw.css553.backend.manager;

import java.util.List;
import java.util.ArrayList;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.Serializable;

import edu.uw.css553.backend.entities.Workflow;

public interface WorkflowManager {

    /**
     * Responsible for saving a workflow to the data store
     */
    public void saveWorkflow( Workflow workflow );

    /**
     * Responsible for retrieving workflow from datastore
     */
    public Workflow openWorkflow( String directory );

    /**
     * Responsible for retrieving all workflows from the data store
     */
    public List<Workflow> listAvailableWorkflows( String directory );

}