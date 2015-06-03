package edu.uw.css553.backend.manager;

import edu.uw.css553.backend.entities.Workflow;
import edu.uw.css553.frontend.SessionFactoryManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clbur_000 on 6/2/2015.
 */
public class WorkflowManagerDbImpl implements WorkflowManager {
    boolean DEBUG = false;
    private SessionFactory factory = null;
    private Session session = null;

    public WorkflowManagerDbImpl() {
        // Empty constructor
    }

    public WorkflowManagerDbImpl (SessionFactory factory) {
        this.factory = factory;
        session = factory.openSession();
    }

    @Override
    public void saveWorkflow(Workflow workflow) {
        if (!session.isOpen())
            session = factory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(workflow);
            System.out.println("Finished saving workflow " + workflow.getName());
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("There was an error saving the workflow. Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public Workflow openWorkflow(String workflowName) {
        Workflow workflow = new Workflow(workflowName);
        return workflow;
    }

    /**
     * Very rigid search; does not recurse through subdirectories.
     */
    @Override
    public List<Workflow> listAvailableWorkflows(String userName) {

        List<Workflow> result = new ArrayList<Workflow>();
        try {
            if (!session.isOpen())
                session = factory.openSession();
            Query query = session.createQuery("from Workflow where createdBy = :user");
            query.setParameter("user", userName);
            result = query.list();
        } catch (Exception e) {
            System.out.println("There was an error accessing the database. " +
                    "Please try again.");
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

        return result;
    }
}
