/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uw.css553.frontend;

import edu.uw.css553.backend.entities.ActionFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Main runner that runs the GUI Application
 * @author Zulqurnain Hussain
 */
public class ProvCliRunner extends Application{
    private static SessionFactory factory;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ProvenanceClientFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Configuration cfg = new Configuration();
//        cfg.addResource("Action.hbm.xml");
//        cfg.addResource("Workflow.hbm.xml");
//        cfg.addResource("ExecutionLog.hbm.xml");
//        cfg.addResource("LogStep.hbm.xml");
//        cfg.setProperties(System.getProperties());
//
//        try {
//            factory = cfg.buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
        launch(args);
        System.out.print(ActionFactory.getActionList().size());
    }
    
}
