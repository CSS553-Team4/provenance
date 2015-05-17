package edu.uw.css553.backend.runner;

import java.util.Calendar;

import edu.uw.css553.backend.entities.Action;
import edu.uw.css553.backend.entities.Workflow;
import edu.uw.css553.backend.logger.FileLogger;

/**
 * @author Christina Burnett
 * @version "1.0, 05/17/15"
 *
 * The Runner class implements the RunnerInterface.
 * This class performs the actions defined in a given workflow, logging each
 * action via the Logger class.
 */
public class Runner implements RunnerInterface {
    @Override
    public int executeWorkflow (Workflow workflow) {
        int i = 1;
        int success = 1;
        Action action;
        Object outputObject = new Object();
        int actionCount = workflow.getActions().size();
        if (actionCount > 0) {
            Calendar calendar = Calendar.getInstance();
            String filename = "theFileName.txt"; //TODO determine pattern for filename construction
            Object inputObject = new Object(); //TODO this must be the input data for the Workflow
            // instantiate the logger
            FileLogger logger = new FileLogger(filename);
            // initialize log for this workflow
            //TODO Logger expects unique number for Workflow, but Workflow does not supply one
            logger.initWorkflowLog( 1 , calendar.getTime());
            while (i <= actionCount && success == 1) {
                action = workflow.getActions().get(i);
                // initialize action
                //TODO Logger expects Action name, but Action does not supply one
                logger.initAction(action.toString(), calendar.getTime());
                try {
                    // execute action
                    outputObject = action.execute(inputObject);
                }
                catch (RuntimeException ex) {
                    System.out.println("Runtime exception in step " + i + " of " + workflow.getName());
                    System.out.println(ex.toString());
                    success = 0;
                }
                // terminate action
                logger.terminateAction(outputObject.toString(), calendar.getTime());
                // prepare for next action by passing output into next step's input
                inputObject = outputObject;
                i++;
            }
            // terminate workflow
            logger.terminateWorkflowLog(outputObject.toString(), calendar.getTime());
        }
        return success;
    }
}

