package edu.uw.css553.backend.logger;

import java.sql.Timestamp;
import java.util.Date;
import edu.uw.css553.backend.entities.ExecutionLog;
import edu.uw.css553.backend.entities.LogStep;
import edu.uw.css553.backend.entities.LogParameter;

import javax.print.DocFlavor;

/**
 * Created by clbur_000 on 5/30/2015.
 */
public class DbLogger implements Logger {
    ExecutionLog log;
    LogStep step;
    LogParameter param;
    int stepCount;

    DbLogger () {
        stepCount = 0;
    }
    /**
     * Opens a log entry for a given workflow id, leaving it ready to begin
     * receiving details of the actions that will be executed.
     * @param workflowId The id of the Workflow being logged
     * @param start The start time of the log entry
     */
    @Override
    public void initWorkflowLog(String workflowId, Date start) {
        log = new ExecutionLog();
        log.setWorkflowId(workflowId);
        log.setExecutionStartTime((Timestamp)start);
    }

    /**
     * Logs the initialization of an action in the workflow.
     * @param name The name of the action
     * @param start The start time of the action
     */
    @Override
    public void initAction(String name, Date start) {
        step = new LogStep();
        step.setName(name);
        step.setExecutionStartTime((Timestamp)start);
        step.setSequence(stepCount);
        stepCount++;
    }

    /**
     * Logs the completion of a action in the workflow.
     * @param result A string detailing the result of the action
     * @param end The end time of the action
     */
    @Override
    public void terminateAction(String result, Date end) {
        step.setReturnValue(result);
        step.setExecutionEndTime((Timestamp)end);
    }

    /**
     * Logs the completion of the workflow, submitting the log entry to the
     * Provenance data store.
     * @param result A string detailing the result of the workflow
     * @param end The end time of the workflow
     */
    @Override
    public void terminateWorkflowLog(String result, Date end) {
        log.setReturnValue(result);
        log.setExecutionEndTime((Timestamp)end);
        log.setStepCount(stepCount);
    }

}
