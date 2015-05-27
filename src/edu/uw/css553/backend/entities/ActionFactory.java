/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uw.css553.backend.entities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is in charge of knowing all the actions that are created
 * @author lando
 */
public class ActionFactory {
    
    private static HashMap registeredActions;
    static{
        registeredActions = new HashMap();
    }
    
    public static void register(String actionName ,Action action){
        registeredActions.put(actionName, action);
    }
    public static Action createAction(String actionType){
        Action a = ((Action)registeredActions.get(actionType));
        return a.createAction();
    }
    public static List<String> getActionList(){
        List<String> listOfActions = new ArrayList<>(registeredActions.keySet());
        return listOfActions;
    }
    
}
