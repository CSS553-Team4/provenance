import java.util.Date;

/**
 * The Logger is responsible for recording details from a workflow's execution
 * to persistent storage.
 * @author Mark Cafaro
 */

public interface Logger {

    /**
     * Opens a log entry for a given workflow, leaving it ready to begin
     * receiving details of the action that will be executed.
     * @param workflowId The id of the Workflow being logged
     * @param inputUrl ???
     * @param start The start time of the log entry
     * @param notes Any notes to include with the initialization of the log
     * @return The execution id of the workflow entry
     */
    public int initWorkflowLog(int workflowId, String inputUrl, Date start, String notes);

    /**
     * Logs the initialization of an action in the workflow.
     * @param execId The id of the workflow running the action
     * @param sequenceNum The sequence number of the action in the workflow
     * @param actionUrl ???
     * @param outputUrl ???
     * @param start The start time of the action
     * @return The action execution id of the action entry
     */
    public int initAction(int execId, int sequenceNum, String actionUrl, String outputUrl, Date start);

    /**
     * Logs the completion of a action in the workflow.
     * @param actionId The action execution id of the action being terminated
     * @param end The end time of the action
     * @param result ???
     * @param message Any message to include with the termination of the action
     */
    public void terminateAction(int actionId, Date end, Data result, String message);

    /**
     * Logs the completion of the workflow, submitting the log entry to the
     * Provenance data store.
     * @param execId The execution id of the workflow being terminated
     * @param end The end time of the workflow
     * @param result ???
     * @param message Any message to include with the termination of the workflow
     */
    public void terminateWorkflowLog(int execId, Date end, Date result, String message);

}