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
 * A simple Logger implementation that writes logs to a specified local
 * directory.
 */
public class LoggerImpl implements Logger {

    private String logDirectory;

    private PrintWriter currentWriter;

    /**
     * Constructs a new Logger. No files are created until initWorkflowLog is
     * called.
     * @param directory The directory used to store log files.
     */
    public LoggerImpl(String directory)
    {
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException("Directory does not exist");
        }
        logDirectory = directory;
    }

    /**
     * Creates the workflow log file with the filename format "workflowId-date".
     * @param workflowId The id of the Workflow being logged
     * @param start The start time of the log entry
     */
    @Override
    public void initWorkflowLog(int workflowId, Date start) {
        if (isLogOpen()) {
            throw new IllegalStateException("A workflow log is currently open");
        }

        DateFormat format = new SimpleDateFormat();
        String logName = Integer.toString(workflowId) + "-" + format.format(start);
        try {
            currentWriter = new PrintWriter(new File(logDirectory, logName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Writer could not be created");
        }
    }

    @Override
    public void initAction(String name, Date start) {
        if (!isLogOpen()) {
            throw new IllegalStateException("No open workflow log");
        }
    }

    @Override
    public void terminateAction(String result, Date end) {
        if (!isLogOpen()) {
            throw new IllegalStateException("No open workflow log");
        }
    }

    @Override
    public void terminateWorkflowLog(String result, Date end) {
        if (!isLogOpen()) {
            throw new IllegalStateException("No open workflow log");
        }
    }

    public boolean isLogOpen() {
        return currentWriter != null;
    }

}