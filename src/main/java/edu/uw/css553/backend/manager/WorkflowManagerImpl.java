package edu.uw.css553.backend.manager;

public class WorkflowManagerImpl implements WorkflowManager {

    private final String WORKFLOW_DIR = "~/";   // how to account for OS? pending final path decision.
    private final boolean DEBUG = false;        // 'true' to print debug statements

    public WorkflowManagerImpl() {
        // Empty constructor
    }

    public void saveWorkflow( Workflow workflow ) {
        try {
            FileOutputStream out = new FileOutputStream( WORKFLOW_DIR + "/" + workflow.getName() + ".wfl" );
            ObjectOutputStream oos = new ObjectOutputStream( out );
            oos.writeObject( workflow );
            oos.close();
            System.out.println( "Finished saving workflow "+ workflow.getName() +
                " to directory " + WORKFLOW_DIR );
        } catch ( Exception e ) {
            System.out.println( "There was an error saving the workflow. Please try again." );
            if ( DEBUG ) { e.printStackTrace(); }
        }

    }

    public Workflow openWorkflow( String path ) {
        // TODO: File path validation

        Workflow workflow = null;

        try {
            FileInputStream in = new FileInputStream( path );
            ObjectInputStream ois = new ObjectInputStream( in );
            workflow = ( Workflow ) ois.readObject();
            ois.close();
        } catch ( FileNotFoundException fnfe ) {
            System.out.println( "There was an error opening the file you specified. " +
                "Please check that the provided path is correct." );
            System.out.println( "Requested path: " + path );
            if ( DEBUG ) { fnfe.printStackTrace(); }
        } catch ( Exception e ) {
            if ( DEBUG ) { e.printStackTrace(); }
        }

        return workflow;
    }

    public List<Workflow> listAvailableWorkflows( String directory ) {
        // TODO: File path validation

        ArrayList<Workflow> result = new ArrayList<Workflow>();

        try {
            File folder = new File( directory );
            File[] listOfFiles = folder.listFiles();

            for ( int i = 0; i < listOfFiles.length; i++ ) {
                File file = listOfFiles[i];
                if ( file.isFile() && file.matches( ".*\\.wfl" ) ) {
                    result.add( openWorkflow( file.getAbsolutePath() ) );
                    if ( DEBUG ) { System.out.println( "Adding file: " + file.getName() ); }
                }
            }
        } catch ( Exception e ) {
            System.out.println( "There was an error accessing the directory you provided. " +
                "Please try again." );
        }

        return result;
    }
}