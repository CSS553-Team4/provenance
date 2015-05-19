package edu.uw.css553.frontend;

import edu.uw.css553.backend.entities.Workflow;
import edu.uw.css553.backend.manager.WorkflowManager;
import edu.uw.css553.backend.manager.WorkflowManagerImpl;
import java.util.List;
import java.util.Scanner;
/**
 * CSS 553 command line interface
 * @version "0.01 5/19/2015"
 * @author Zulqurnain Hussain
 */
public class ProvenanceCli {

    public static void main( String[] args ) {
        boolean quitProgram = false;
        Scanner in = new Scanner(System.in);
        Workflow work;
        WorkflowManagerImpl manager = new WorkflowManagerImpl();
        
        System.out.println("Welcome to CSS 553 Javerna Provenance tool");
        System.out.println("Type 'quit' to exit the program");
        System.out.println("Type 'help' to get a full list of available commands");
        //TODO: Implement workflow creation, saving, and execution
        //-Actions
        //TODO: Implement lisiting 
        
        while ( !quitProgram ) {
            
            String input = in.nextLine();
            if(input.matches("[qQ]uit") == true){
                quitProgram = true;
                continue;
            }
            else if(input.matches("[Hh]elp") == true){
                System.out.println("'create' - design a workflow\n"
                        + "'quit' - exits the program\n"
                        + "'list' - lists the saved workflows");
            }
            else if(input.matches("[Cc]reate") == true){//todo
                System.out.println("Enter Workflow name:");
                String name = in.nextLine();
                work = new Workflow(name);
            }
            else if(input.matches("[lL]ist") == true){//TODO
                System.out.println("Enter in a file directory location:");
                String loc = in.nextLine();
                List<Workflow> workflows = manager.listAvailableWorkflows(loc);
                for(Workflow w : workflows){
                    System.out.println(w.getName());
                }
            }
            else{
                System.out.println("Invalid input, enter 'help' to view list of commands");
            }
        }

    }
    public static void AddActions(Scanner in, Workflow workflow){
        
    }

}
