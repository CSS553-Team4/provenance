/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uw.css553.frontend;

import edu.uw.css553.backend.entities.Action;
import edu.uw.css553.backend.entities.ActionFactory;
import edu.uw.css553.backend.entities.Workflow;
import edu.uw.css553.backend.entities.WorkflowParameter;
import edu.uw.css553.backend.manager.WorkflowManagerImpl;
import edu.uw.css553.backend.runner.Runner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zulqurnain Hussain
 */
public class ProvenanceClientFXMLController {

    @FXML
    MenuItem AboutProgram;
    @FXML
    MenuItem CloseProgram;
    @FXML
    Group ActionGroup;
    @FXML
    MenuItem ExecuteWorkflow;
    @FXML
    Button OpenWorkflowButton;
    @FXML
    Button SaveWorkflowButton;
    @FXML
    MenuItem DeleteAction;
    @FXML
    ListView<String> ActionList;
    @FXML
    MenuItem SaveWorkflow;
    @FXML
    Button ExecuteWorkflowButton;
    @FXML
    MenuItem OpenWorkflow;
    @FXML
    VBox WorkflowFlowPane;

    //Array list of the VFlow nodes
    ArrayList<Object> WorkflowActionNodes;
    //Holds the class for deletion of WorkflowNodes
    RectangleActionNodeHelper[] holder;
    //Workflow Instance class
    Workflow workflow;
    //WorkflowManager Instance Class
    WorkflowManagerImpl manager = new WorkflowManagerImpl();
    //Runner Instance
    Runner run = new Runner();
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        assert AboutProgram != null : "fx:id=\"AboutProgram\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert CloseProgram != null : "fx:id=\"CloseProgram\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert ActionGroup != null : "fx:id=\"ActionGroup\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert ExecuteWorkflow != null : "fx:id=\"ExecuteWorkflow\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert OpenWorkflowButton != null : "fx:id=\"OpenWorkflowButton\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert SaveWorkflowButton != null : "fx:id=\"SaveWorkflowButton\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        assert DeleteAction != null : "fx:id=\"DeleteAction\" was not injected: check your FXML file 'ProvenanceClientFXML'.";
        holder = new RectangleActionNodeHelper[1];
        WorkflowActionNodes = new ArrayList<>();
        WorkflowFlowPane.setSpacing(10.0);
        populateActionList();

    }
    /**
     * Executes the given workflow
     * @param event 
     */
    @FXML
    void onActionExecuteWorkflow(ActionEvent event){
        if(workflow == null){
            saveWorkflow(event);
        }
        TextInputDialog dialog = new TextInputDialog("Test Input");
        dialog.setTitle("Enter in workflow input");
        dialog.setContentText("Workflow Input: ");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Choose logging directory");
            File f = chooser.showDialog(new Stage());
            if(f == null){
                return;
            }
            System.out.println(f.getPath());
            run.setLogDirectory(f.getPath());
            workflow.setWorkflowInput(result.get(), false);
            System.out.println(result.get());
            Object o = run.executeWorkflow(workflow);
            System.out.println(o);
        }
        event.consume();
    }
    /**
     * Delete a selected workflow item
     * @param event 
     */
    @FXML
    void onActionDeleteWorkflowActionNodes(ActionEvent event){
        if(holder[0] != null){
            if(workflow != null){
                try {
                    workflow.removeAction(holder[0].getAction());
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            WorkflowFlowPane.getChildren().remove(holder[0].getStackPane());
            WorkflowActionNodes.remove(holder[0]);
            holder[0] = null;
        }
    }
    
    /**
     * If draged an action name into the main pane create a workflow action entry
     * in the workflow list
     *
     * @param event
     */
    @FXML
    void onDragDroppedWorkflowPane(DragEvent event) {
        System.out.println("On drag dropped");
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            Action action = ActionFactory.createAction(db.getString());
            RectangleActionNodeHelper rectHelper = new RectangleActionNodeHelper(action, db.getString());
            WorkflowFlowPane.getChildren().add(rectHelper.getStackPane());
            WorkflowActionNodes.add(rectHelper);
            rectHelper.setHolder(holder);
            //System.out.println("Length: "+holder[0]);
        }
        event.setDropCompleted(true);
        event.consume();
    }

    /**
     * Drag is detected, start drag-and-grop gesture
     *
     * @param event : Mouse drag event
     */
    @FXML
    void onDragDetectedActionList(MouseEvent event) {
        System.out.println("Drag detected");
        //Allow transfer mode
        Dragboard db = ActionList.startDragAndDrop(TransferMode.ANY);
        //Put selected list item in the clipboard
        ClipboardContent content = new ClipboardContent();
        content.putString(ActionList.getSelectionModel().getSelectedItem());
        db.setContent(content);

        event.consume();
    }

    /**
     * Drag over event
     *
     * @param event : Drag over event
     */
    @FXML
    void onDragOverWorkflowFlowPane(DragEvent event) {
        System.out.println("on Drag over Detected");

        if (event.getGestureSource() == ActionList
                && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    /**
     * Loads the workflow and displays the workflow actions
     *
     * @param event
     */
    @FXML
    void openWorkflow(ActionEvent event) {
        WorkflowFlowPane.getChildren().clear();
        WorkflowActionNodes.clear();
        holder[0] = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Workflow File");
        File f = fileChooser.showOpenDialog(new Stage());
        if(f==null){
            event.consume();
            return;
        }
        workflow = manager.openWorkflow(f.getPath());
        List<Action> workflowAction = workflow.getActions();
        for(Action e : workflowAction){
            RectangleActionNodeHelper rect = new RectangleActionNodeHelper(e, e.getName());
            System.out.println("Iter problem");
            ListIterator iter = e.getParameters().listIterator();
            if(iter.hasNext()){
                WorkflowParameter wP = (WorkflowParameter)iter.next();
                rect.setTextFieldInput(wP.getName(), wP.getValue());
            }
            WorkflowActionNodes.add(e);
            WorkflowFlowPane.getChildren().add(rect.getStackPane());
        }
    }
    /**
     * Saves a workflow to a given location
     * @param event 
     */
    @FXML
    void saveWorkflow(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("WFL files (*.wfl)", "*.wfl");
        fileChooser.getExtensionFilters().add(extFiler);
        File file = fileChooser.showSaveDialog(new Stage());
        if(file==null){
            event.consume();
            return;
        }
        System.out.println(file.getName());
        String name = file.getName().replaceAll("\\..*", "");
        workflow = new Workflow(name);
        for (Object r : WorkflowActionNodes) {
            RectangleActionNodeHelper rect = (RectangleActionNodeHelper) r;
            workflow.addAction(rect.getAction());
        }
        if(workflow.getActions().size() ==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Action Values Error");
            alert.setHeaderText("Actions are not filled in, using default values");
            alert.showAndWait();
            event.consume();
            return;
        }
        manager.saveWorkflow(workflow, file.getPath());
        
    }
    
    /**
     * Populates the list with all the actions in the actions package
     */
    void populateActionList() {

        String topLevelDir = System.getProperty("user.dir") + "\\src\\edu\\uw\\css553\\backend\\actions";
        File dir = new File(topLevelDir);
        List<String> actionList;
        Class actionInitializer;
        try {
            actionList = findClasses(dir, "edu.uw.css553.backend.actions");
            for (String s : actionList) {
                actionInitializer = Class.forName(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ObservableList<String> actionItems = FXCollections.observableArrayList(ActionFactory.getActionList());
        ActionList.setItems(actionItems);

    }

    /**
     * Recursive method used to find all classes as a string in a given
     * directory and subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base
     * directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<String> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<String> classes = new ArrayList<String>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
//        System.out.println(files);
        for (File file : files) {
            if (file.getName().endsWith(".java")) {
                classes.add(packageName + '.' + file.getName().substring(0, file.getName().length() - 5));
            }
//            System.out.println(file.getName());
        }
        System.out.println(classes);
        return classes;
    }

    class RectangleActionNodeHelper {
        
        public Rectangle rectangle;
        public TextField parameter1;
        public TextField parameter2;
        public StackPane stackPane;
        private VBox Lister;
        private Button select;
        Action workflowAction;
        RectangleActionNodeHelper[] focusedClass;
        boolean selected = false;
        public RectangleActionNodeHelper(Action action, String name) {
            Stop[] stops = new Stop[]{new Stop(0, Color.DARKGRAY), new Stop(1, Color.WHITE)};
            LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
            this.workflowAction = action;
            rectangle = new Rectangle(500, 200);
            rectangle.setFill(lg1);
            parameter1 = new TextField("scriptText");
            parameter2 = new TextField("InputVal");
            Text Nodename = new Text(name);
            select = new Button("Select");
            Lister = new VBox(5.0, Nodename, parameter1, parameter2,select);
            
            stackPane = new StackPane(rectangle);
            stackPane.getChildren().add(Lister);

            parameter1.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                    System.out.println("Change Text");
                    List<WorkflowParameter> params = new ArrayList<>();
                    WorkflowParameter param = new WorkflowParameter();
                    param.setName(newValue);
                    param.setValue(parameter2.getText());
                    params.add(param);
                    workflowAction.setParameters(params);
                }
            });

            parameter2.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                    System.out.println("Change Text Param 2 " + newValue);
                    List<WorkflowParameter> params = new ArrayList<>();
                    WorkflowParameter param = new WorkflowParameter();
                    param.setName(parameter1.getText());
                    param.setValue(newValue);
                    params.add(param);
                    workflowAction.setParameters(params);
                }
            });
            select.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if(!selected){
                        if(focusedClass[0] != null){
                            Stop[] stops = new Stop[]{new Stop(0, Color.DARKGRAY), new Stop(1, Color.WHITE)};
                            LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                            focusedClass[0].rectangle.setFill(lg1);
                            focusedClass[0].selected = false;
                        }
                        Stop[] stops = new Stop[]{new Stop(0, Color.BLUE), new Stop(1, Color.WHITE)};
                        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                        rectangle.setFill(lg1);
                        selected = true;
                        focusedClass[0] = returnSelf();
                    }
                    else{
                        Stop[] stops = new Stop[]{new Stop(0, Color.DARKGRAY), new Stop(1, Color.WHITE)};
                        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                        rectangle.setFill(lg1);
                        selected = false;
                        focusedClass[0] = null;
                    }
                }
            });
            
        }
        public void setTextFieldInput(String name, String value){
            parameter1.setText(name);
            parameter2.setText(value);
        }
        public StackPane getStackPane() {
            return stackPane;
        }

        public Action getAction() {
            return workflowAction;
        }

        public void setHolder(RectangleActionNodeHelper[] rectHolder) {
            focusedClass = rectHolder;
        }

        public RectangleActionNodeHelper returnSelf() {
            return this;
        }
    }
}
