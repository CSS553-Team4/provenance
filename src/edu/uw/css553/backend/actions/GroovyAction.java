package edu.uw.css553.backend.actions;

import edu.uw.css553.backend.entities.Action;
import edu.uw.css553.backend.entities.ActionFactory;
import edu.uw.css553.backend.entities.WorkflowParameter;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.Serializable;
import java.util.List;

import java.util.Map;

/**
 * An action implementation that runs a Groovy script with the given input and
 * returns the script result.
 *
 * Usage:
 * GroovyAction action = new GroovyAction();
 *
 * Map<String, Object> params = new HashMap<>();
 * params.put("scriptText", "input.toUpperCase()");
 * action.setParameters(params);
 *
 * Object result = action.execute("hello world!");
 *
 * assertEquals(result, "HELLO WORLD!");
 */
public class GroovyAction extends Action {

    
    String scriptText;
    static {
        ActionFactory.register("Groovy", new GroovyAction());
        System.out.println("Added Groovy");
    }
    public GroovyAction () {
        name = "Groovy";
    }

    /**
     * Evaluates the groovy script text and returns the result. The input is
     * bound to a variable named "input" in the script.
     * @param input
     * @return
     */
    public Object execute(Object input) {
        Binding data = new Binding();
        data.setProperty("input", input);
        GroovyShell shell = new GroovyShell(data);
        return shell.evaluate(scriptText);
    }
    
    
    /**
     * Set the script parameters. The only key recognized is 'scriptText' which
     * should contain the actual text of the script to run. All other keys are
     * ignored.
     * @param params A map of the parameters
     */
    @Override
    public void setParameters(List<WorkflowParameter> params) {
        if(params.size() >= 1){
            if(!params.get(0).getName().matches("scriptText")){
                throw new IllegalArgumentException("Params must contain a scriptText key");
            }
            this.params = params;
            scriptText = params.get(0).getValue();
        }
        else{
            throw new IllegalArgumentException("Must provide scriptText and function");
        }
//        if (!params.containsKey("scriptText")) {
//            throw new IllegalArgumentException("Params must contain a scriptText key");
//        }
//        scriptText = (String)params.get("scriptText");
    }
    public GroovyAction createAction(){
        return new GroovyAction();
    }

}
