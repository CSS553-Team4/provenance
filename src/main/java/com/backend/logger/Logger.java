import java.util.Date;

/**
 * The Logger is responsible for recording details from a workflow's execution
 * to persistent storage.
 * @author Mark Cafaro
 */

public interface Logger {

    /**
     * Opens a log entry for a given workflow id, leaving it ready to begin
     * receiving details of the actions that will be executed.
     * @param workflowId The id of the Workflow being logged
     * @param start The start time of the log entry
     */
    public void initWorkflowLog(int workflowId, Date start);

    /**
     * Logs the initialization of an action in the workflow.
     * @param name The name of the action
     * @param start The start time of the action
     */
    public void initAction(String name, Date start);

    /**
     * Logs the completion of a action in the workflow.
     * @param result A string detailing the result of the action
     * @param end The end time of the action
     */
    public void terminateAction(String result, Date end);

    /**
     * Logs the completion of the workflow, submitting the log entry to the
     * Provenance data store.
     * @param result A string detailing the result of the workflow
     * @param end The end time of the workflow
     */
    public void terminateWorkflowLog(String result, Date end);

}