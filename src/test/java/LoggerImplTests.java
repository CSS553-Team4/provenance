import edu.uw.css553.backend.logger.Logger;
import edu.uw.css553.backend.logger.LoggerImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.util.Date;

/**
 * Created by markcafaro on 5/16/15.
 */
public class LoggerImplTests {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenDirectoryDoesNotExist() {
        Logger logger = new LoggerImpl("/folder/does/not/exist");
    }

    @Test (expected = IllegalStateException.class)
    public void throwsWhenLoggingActionWithoutWorkflow()
    {
        Logger logger = new LoggerImpl(folder.getRoot().getAbsolutePath());
        logger.initAction("action", new Date());
    }

    @Test
    public void createsProperlyNamedLogFile()
    {
        Logger logger = new LoggerImpl(folder.getRoot().getAbsolutePath());
    }

}
