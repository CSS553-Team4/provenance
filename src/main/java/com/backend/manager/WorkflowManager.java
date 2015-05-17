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