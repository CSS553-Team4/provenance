import edu.uw.css553.backend.entities.GroovyAction;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GroovyActionTests {

    @Test
    public void runScriptWithNumericInput() {
        GroovyAction action = new GroovyAction();
        Map<String, Object> params = new HashMap<>();
        params.put("scriptText", "input*2");
        action.setParameters(params);

        Object result = action.execute(5);

        assertEquals(result, 10);
    }

    @Test
    public void runScriptWithStringInput() {
        GroovyAction action = new GroovyAction();
        Map<String, Object> params = new HashMap<>();
        params.put("scriptText", "input.toUpperCase()");
        action.setParameters(params);

        Object result = action.execute("hello world!");

        assertEquals(result, "HELLO WORLD!");
    }

    @Test
    public void runMultilineScript() {
        GroovyAction action = new GroovyAction();
        Map<String, Object> params = new HashMap<>();
        params.put("scriptText", "a = input.length(); a * 2");
        action.setParameters(params);

        Object result = action.execute("wow");

        assertEquals(result, 6);
    }

}
