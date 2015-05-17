package edu.uw.css553.backend.runner;

import edu.uw.css553.backend.entities.Workflow;

/**
 * @author Christina Burnett
 * @version "1.0, 05/16/15"
 *
 * The RunnerInterface defines the public interface which will be implemented
 * by the Runner class.
 * The Runner class has dependencies on Workflow, Action, and Logger classes.
 * The Runner class performs the series of actions defined in a given workflow,
 * logging each action via the Logger class.
 *
 */
public interface RunnerInterface {
    /*
    * Executes the actions defined within an instance of the Workflow class.
    * @param workflow An instance of a Workflow
    * @return int The return code of the last action executed in the workflow.
    */
    public abstract int executeWorkflow (Workflow workflow);
}