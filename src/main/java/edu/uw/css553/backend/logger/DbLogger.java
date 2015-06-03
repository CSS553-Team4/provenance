package edu.uw.css553.backend.logger;

import java.sql.Timestamp;
import java.util.Date;
import edu.uw.css553.backend.entities.ExecutionLog;
import edu.uw.css553.backend.entities.LogStep;
import edu.uw.css553.backend.entities.LogParameter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.print.DocFlavor;

/**
 * Created by clbur_000 on 5/30/2015.
 */
public class DbLogger implements Logger {
    boolean DEBUG = false;
    private SessionFactory factory = null;
    private Session session = null;

    ExecutionLog log;
    LogStep step;
    LogParameter param;
    int stepCount;

    public DbLogger () {
        stepCount = 0;
    }
    public DbLogger (SessionFactory factory) {
        this.factory = factory;
        session = factory.openSession();
        stepCount = 0;
    }
    /**
     * Opens a log entry for a given workflow id, leaving it ready to begin
     * receiving details of the actions that will be executed.
     * @param workflowId The id of the Workflow being logged
     * @param start The start time of the log entry
     */
    @Override
    public void initWorkflowLog(String workflowId, Date start) {
        if (!session.isOpen())
            session = factory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            log = new ExecutionLog();
            log.setWorkflowId(workflowId);
            log.setExecutionStartTime((Timestamp)start);
            session.save(log);
            System.out.println("Finished saving log");
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("There was an error saving the log. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Logs the initialization of an action in the workflow.
     * @param name The name of the action
     * @param start The start time of the action
     */
    @Override
    public void initAction(String name, Date start) {
        if (!session.isOpen())
            session = factory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            step = new LogStep();
            step.setName(name);
            step.setExecutionStartTime((Timestamp)start);
            step.setSequence(stepCount);
            session.save(step);
            System.out.println("Finished saving log step");
            tx.commit();
            stepCount++;
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("There was an error saving the log step. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Logs the completion of a action in the workflow.
     * @param result A string detailing the result of the action
     * @param end The end time of the action
     */
    @Override
    public void terminateAction(String result, Date end) {
        if (!session.isOpen())
            session = factory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            step.setReturnValue(result);
            step.setExecutionEndTime((Timestamp)end);
            session.update(step);
            System.out.println("Finished saving log step");
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("There was an error saving the log step. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Logs the completion of the workflow, submitting the log entry to the
     * Provenance data store.
     * @param result A string detailing the result of the workflow
     * @param end The end time of the workflow
     */
    @Override
    public void terminateWorkflowLog(String result, Date end) {
        if (!session.isOpen())
            session = factory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            log.setReturnValue(result);
            log.setExecutionEndTime((Timestamp)end);
            log.setStepCount(stepCount);
            session.update(log);
            System.out.println("Finished saving log");
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("There was an error saving the log. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

}
