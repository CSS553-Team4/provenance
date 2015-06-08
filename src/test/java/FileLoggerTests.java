import edu.uw.css553.backend.logger.Logger;
import edu.uw.css553.backend.logger.FileLogger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class FileLoggerTests {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test (expected = IllegalArgumentException.class)
    public void throwsWhenDirectoryDoesNotExist() {
        FileLogger logger = new FileLogger("/folder/does/not/exist");
    }

    @Test (expected = IllegalStateException.class)
    public void throwsWhenInitLogWithCurrentLog() {
        FileLogger logger = new FileLogger(folder.getRoot().getAbsolutePath());
        logger.initWorkflowLog(UUID.randomUUID().toString(), new Date());
        logger.initWorkflowLog(UUID.randomUUID().toString(), new Date());
    }

    @Test (expected = IllegalStateException.class)
    public void throwsWhenLoggingActionWithoutWorkflow()
    {
        FileLogger logger = new FileLogger(folder.getRoot().getAbsolutePath());
        logger.initAction("action", new Date());
    }

    @Test
    public void createsSimpleLog() throws IOException {
        FileLogger logger = new FileLogger(folder.getRoot().getAbsolutePath());

        Date start = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        logger.initWorkflowLog(UUID.randomUUID().toString(), calendar.getTime());

        calendar.add(Calendar.MINUTE, 5);
        logger.initAction("action1", calendar.getTime());

        calendar.add(Calendar.MINUTE, 5);
        logger.terminateAction("action1 result", calendar.getTime());

        calendar.add(Calendar.MINUTE, 5);
        logger.terminateWorkflowLog("action", calendar.getTime());

        File log = logger.getCurrentLog();
        List<String> lines = Files.readAllLines(log.toPath(), Charset.defaultCharset());

        assertEquals(lines.size(), 4);
    }

    @Test
    public void createsSeparateLogFiles() {
        FileLogger logger = new FileLogger(folder.getRoot().getAbsolutePath());

        logger.initWorkflowLog(UUID.randomUUID().toString(), new Date());
        logger.terminateWorkflowLog("result", new Date());
        File log1 = logger.getCurrentLog();

        logger.initWorkflowLog(UUID.randomUUID().toString(), new Date());
        logger.terminateWorkflowLog("result", new Date());
        File log2 = logger.getCurrentLog();

        assertNotEquals(log1.getAbsolutePath(), log2.getAbsolutePath());
    }

}
