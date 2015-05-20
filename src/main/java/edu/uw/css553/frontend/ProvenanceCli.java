package edu.uw.css553.frontend;

import edu.uw.css553.backend.entities.Action;
import edu.uw.css553.backend.entities.GroovyAction;
import edu.uw.css553.backend.entities.Workflow;
import edu.uw.css553.backend.manager.WorkflowManager;
import edu.uw.css553.backend.manager.WorkflowManagerImpl;
import edu.uw.css553.backend.runner.Runner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

/**
 * CSS 553 command line interface
 *
 * @version "0.01 5/19/2015"
 * @author Zulqurnain Hussain
 */
public class ProvenanceCli {

    public static void main(String[] args) {
        boolean quitProgram = false;
        Scanner in = new Scanner(System.in);
        Workflow work;
        WorkflowManagerImpl manager = new WorkflowManagerImpl();
        Runner run = new Runner();
        run.setLogDirectory(System.getProperty("user.dir"));
        
        System.out.println("Welcome to CSS 553 Provenance tool");
        System.out.println("Type 'quit' to exit the program");
        System.out.println("Type 'help' to get a full list of available commands");
        //TODO: Implement workflow creation, saving, and execution
        //-Actions
        //TODO: Implement lisiting 

        while (!quitProgram) {
            System.out.println("---Main Menu---");
            String input = in.nextLine();
            if (input.matches("[qQ]uit") == true) {
                quitProgram = true;
                continue;
            } else if (input.matches("[Hh]elp") == true) {
                System.out.println("'create' - design a workflow\n"
                        + "'quit' - exits the program\n"
                        + "'list' - lists the saved workflows");
            } else if (input.matches("[Cc]reate") == true) {//todo
                System.out.println("Enter Workflow name:");
                String name = in.nextLine();
                work = new Workflow(name);
                int id = 1 + (int) (Math.random() * 1000);
                work.workflowId = id;

                ProvenanceCli.editWorkflow(in, work, manager, run);
                continue;
            } else if (input.matches("[lL]ist") == true) {//TODO
                System.out.println("Enter in a file directory location:");
                String loc = in.nextLine();
                List<Workflow> workflows = manager.listAvailableWorkflows(loc);
                for (Workflow w : workflows) {
                    System.out.println(w.getName());
                }
                System.out.println("Enter in workflow to edit:");
                String selectedWorkflow = in.nextLine();
                if (workflows.contains(new Workflow(selectedWorkflow))) {
                    work = workflows.get(workflows.indexOf(
                            new Workflow(selectedWorkflow)));
                    ProvenanceCli.editWorkflow(in, work, manager, run);
                } else {
                    System.out.println("Invalid entry returning to main menu");
                    continue;
                }
            } else {
                System.out.println("Invalid input, enter 'help' to view list of commands");
            }
        }

    }

    public static void editWorkflow(Scanner in, Workflow workflow, WorkflowManagerImpl manager, Runner run) {
        workflow.isWorkflowOpen = true;
        List<Action> actions = ProvenanceCli.getActions();
        while (true) {
            System.out.println("'save' - saves the workflow\n"
                    + "'execute' - executes the workflow\n"
                    + "'remove' - removes actions from the workflow\n"
                    + "'add' - allows addition of workflows\n"
                    + "'quit' - exits the edit mode");
            String choice = in.nextLine();
            if (choice.matches("[qQ]uit") == true) {
                return;
            } else if (choice.matches("[Aa]dd") == true) {
                boolean modifyWorkflow = true;
                while (modifyWorkflow) {
//                    System.out.println("Enter the number for the action you wish to add to the workflow:");
//                    for (int i = 0; i < actions.size(); i++) {
//                        System.out.println(i + " " + actions.get(i).getName());
//                    }
//                    int num = in.nextInt();
//                    in.nextLine();
//                    if (num >= actions.size()) {
//                        System.out.print("Invalid action choice");
//                        continue;
//                    }
                    GroovyAction ga = new GroovyAction();
                    
                    System.out.println("Enter in the scriptText parameters");
                    Map<String, Object> params = new HashMap<>();
                    String tmp = in.nextLine();
                    params.put("scriptText", tmp);
                    
                    ga.setParameters(params);
                    workflow.addAction(ga);

                    System.out.print("Actions in " + workflow.getName() + " ");
                    for (Action e : workflow.getActions()) {
                        System.out.print(e.getName() + " ");
                    }
                    System.out.println("\nAre you done adding to the workflow y|n: ");
                    String choice1 = in.nextLine();
                    if (choice1.matches("[Yy].*") == true) {
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (choice.matches("[Rr]emove") == true) {
                while (true) {
                    if (workflow.getActions().size() == 0) {
                        System.out.println("No actions in workflow");
                        break;
                    }
                    System.out.println(workflow.getName() + " contains:");
                    for (int i = 0; i < workflow.getActions().size(); i++) {
                        System.out.println(i + " " + workflow.getActions().get(i).getName());
                    }
                    int num = in.nextInt();
                    in.nextLine();
                    if (num >= workflow.getActions().size() || num <= 0) {
                        System.out.println("Invalid action number, please reenter the number");
                        continue;
                    }
                    workflow.getActions().remove(num);
                    System.out.println("Are you done adding to the workflow y|n: ");
                    in.nextLine(); //nextInt doesn't read nextline so we have to call nextline twice in order for the first one to be consumed
                    String choice1 = in.nextLine();
                    if (choice1.matches("[Yy].*") == true) {
                        break;
                    } else {
                        continue;
                    }
                }
            } else if(choice.matches("[Ee]xecute")){
                System.out.println("Enter in the workflow input");
                String input = in.nextLine();
                workflow.setWorkflowInput(input, false);
                Object o = run.executeWorkflow(workflow);
                System.out.println(o);
                
            } else if(choice.matches("[Ss]ave") == true){
                System.out.println("Attempting to save workflow...");
                manager.saveWorkflow(workflow);
            }else{
                System.out.println("Invalid choice, please try again");
                continue;
            }
        }

    }

    public static List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        //For sake of trying to get this project done, we hard coded the actions
        GroovyAction action = new GroovyAction();
        Map<String, Object> params = new HashMap<>();
        params.put("scriptText", "input.toUpperCase()");
        action.setParameters(params);
        
        actions.add(action);
        return actions;
    }
}



