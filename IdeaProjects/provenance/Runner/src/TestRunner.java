/**
 * @author Christina Burnett
 * @version "1.0, 05/16/15"
 *
 * The TestRunner class performs unit tests on the Runner class.
 */
public class TestRunner {
    public static void main (String[] args) {
        Runner runner = new Runner();
        Workflow workflow = new Workflow();
        int i = runner.executeWorkflow(workflow);
        System.out.print("Return code is " + i);
    }
}
