package edu.uw.css553.backend.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Logger implementation that writes log files to a specified local directory.
 * Each initialized workflow creates a separate log file in the directory.
 */
public class FileLogger implements Logger {

    private String logDirectory;

    private File currentLog;

    private PrintWriter currentWriter;

    private DateFormat dateFormat;

    private boolean isWorkflowOpen;

    private boolean isActionOpen;

    /**
     * Constructs a new Logger. No files are created until initWorkflowLog is
     * called.
     * @param directory The directory used to store log files.
     */
    public FileLogger(String directory)
    {
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException("Directory does not exist");
        }
        logDirectory = directory;
        dateFormat = new SimpleDateFormat();
        isWorkflowOpen = false;
        isActionOpen = false;
    }

    /**
     * Creates the workflow log file with the filename format "workflowId-date".
     * @param workflowId The id of the Workflow being logged
     * @param start The start time of the log entry
     */
    @Override
    public void initWorkflowLog(String workflowId, Date start) {
        if (isWorkflowOpen) {
            throw new IllegalStateException("A workflow is open");
        }

        DateFormat format = new SimpleDateFormat("yyMMddHHmmssZ");
        String logName = workflowId + "-" + format.format(start);
        currentLog = new File(logDirectory, logName);
        try {
            currentWriter = new PrintWriter(currentLog);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Writer could not be created");
        }

        currentWriter.printf("%s: started workflow %d\n", dateFormat.format(start), workflowId);
        isWorkflowOpen = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initAction(String name, Date start) {
        if (isActionOpen) {
            throw new IllegalStateException("An action is open");
        }
        if (!isWorkflowOpen) {
            throw new IllegalStateException("No workflow open");
        }

        currentWriter.printf("%s: started action %s\n", dateFormat.format(start), name);
        isActionOpen = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void terminateAction(String result, Date end) {
        if (!isActionOpen) {
            throw new IllegalStateException("No action open");
        }

        currentWriter.printf("%s: ended action with result: %s\n", dateFormat.format(end), result);
        isActionOpen = false;
    }

    /**
     * Closes the current workflow log file.
     * @param result A string detailing the result of the workflow
     * @param end The end time of the workflow
     */
    @Override
    public void terminateWorkflowLog(String result, Date end) {
        if (isActionOpen) {
            throw new IllegalStateException("An action is open");
        }
        if (!isWorkflowOpen) {
            throw new IllegalStateException("No workflow open");
        }

        currentWriter.printf("%s: ended workflow with result: %s\n", dateFormat.format(end), result);
        currentWriter.close();
        isWorkflowOpen = false;
    }

    public File getCurrentLog()
    {
        return currentLog;
    }

}