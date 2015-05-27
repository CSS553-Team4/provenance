package edu.uw.css553.backend.manager;

import edu.uw.css553.backend.entities.Workflow;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WorkflowManagerImpl implements WorkflowManager {

    private final String WORKFLOW_DIR = "~/";   // how to account for OS? pending final path decision.
    private final String FILENAME_PATTERN = ".*\\.wfl";
    private final boolean DEBUG = false;        // 'true' to print debug statements

    public WorkflowManagerImpl() {
        // Empty constructor
    }

    @Override
    public void saveWorkflow(Workflow workflow) {
        try {
            FileOutputStream out = new FileOutputStream(WORKFLOW_DIR + "/" + workflow.getName() + ".wfl");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(workflow);
            oos.close();
            System.out.println("Finished saving workflow " + workflow.getName()
                    + " to directory " + WORKFLOW_DIR);
        } catch (IOException e) {
            System.out.println("There was an error saving the workflow. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        }

    }

    public void saveWorkflow(Workflow workflow, String path) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(workflow);
            oos.close();
            System.out.println("Finished saving workflow " + workflow.getName()
                    + " to directory " + WORKFLOW_DIR);
        } catch (IOException e) {
            System.out.println("There was an error saving the workflow. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Workflow openWorkflow(String path) {
        // TODO: File path validation

        Workflow workflow = null;

        try {
            FileInputStream in = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(in);
            workflow = (Workflow) ois.readObject();
            ois.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("There was an error opening the file you specified. "
                    + "Please check that the provided path is correct.");
            System.out.println("Requested path: " + path);
            if (DEBUG) {
                fnfe.printStackTrace();
            }
        } catch (IOException ioe) {
            //TODO IOException
            if (DEBUG) {
                ioe.printStackTrace();
            }
        } catch (ClassNotFoundException cnfe) {
            //TODO ClassNotFoundException
            if (DEBUG) {
                cnfe.printStackTrace();
            }
        }

        return workflow;
    }

    /**
     * Very rigid search; does not recurse through subdirectories.
     */
    @Override
    public List<Workflow> listAvailableWorkflows(String directory) {
        // TODO: File path validation

        ArrayList<Workflow> result = new ArrayList<Workflow>();

        try {
            File folder = new File(directory);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (file.isFile() && file.getAbsolutePath().matches(FILENAME_PATTERN)) {
                    result.add(openWorkflow(file.getAbsolutePath()));
                    if (DEBUG) {
                        System.out.println("Adding file: " + file.getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("There was an error accessing the directory you provided. "
                    + "Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
