package edu.uw.css553.backend.entities;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

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
public class GroovyAction implements Action {

    String scriptText;

    /**
     * Evaluates the groovy script text and returns the result. The input is
     * bound to a variable named "input" in the script.
     * @param input
     * @return
     */
    @Override
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
    public void setParameters(Map<String, Object> params) {
        if (!params.containsKey("scriptText")) {
            throw new IllegalArgumentException("Params must contain a scriptText key");
        }
        scriptText = (String)params.get("scriptText");
    }

    @Override
    public String getName() {
        return "Groovy";
    }

}
