/**
 * @author Christina Burnett
 * @version "1.0, 05/16/15"
 *
 * The Runner class implements the RunnerInterface.
 * This class performs the actions defined in a given workflow, logging each
 * action via the Logger class.
 */
public class Runner implements RunnerInterface {
    @Override
    public int executeWorkflow (Workflow workflow) {
        int i = 1;
        int j = 1;
        if (workflow.actions.length > 0) {
            // instantiate the logger
            // initialize log for this workflow
        }
        while (i < workflow.actions.length && j != 0)
        {
            // initialize action
            // execute action
            j = workflow.actions[i].length(); // fake out pending Action interface
            // terminate action
            i++;
        }
        // terminate workflow
        return j;
    }
}

