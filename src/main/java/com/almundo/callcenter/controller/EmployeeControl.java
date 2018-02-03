package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.employee.Roles;
import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import com.almundo.callcenter.controller.employee.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeControl implements IEmployeeControl, Observer {

    public List<Employee> listAgent;

    private static EmployeeControl INSTANCE;

    public EmployeeControl() {
        listAgent = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Static method that obtains the instance of the class only once
     * @return instance EmployeeControl
     */
    public static EmployeeControl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeControl();
        }
        return INSTANCE;
    }

    /**
     * Method that receives notifications of calls to know their status and update the status of agents
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        Call call = ((Call) arg);
        if (call.getStateCall().equals(Status.ANSWERED)) {
            updateStatusEmployee(call.getAgent(), Boolean.TRUE);
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.INFO, "/*****El agente {0} ha contestado la llamada y se encuentra en estado ocupado " + call.getStateCall(), call.getAgent().getName());
        }
        if (call.getStateCall().equals(Status.FINISHED)) {
            updateStatusEmployee(call.getAgent(), Boolean.FALSE);
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.INFO, "/*****El agente {0} ha finalizado la llamada con duracion " + (call.getDurationSeg() / 1000.0) + " Seg y se encuentra disponible para recibir llamadas ", call.getAgent().getName());
        }
    }

    /**
     * Method that allows registering an agent online 
     * @param agent
     */
    @Override
    public void register(Employee agent) {
        listAgent.add(agent);
    }

    /**
     * A method that searches for available agents, 
     * first searches for operators, if there is 
     * no continuous search for agents with a supervisory 
     * role and if it does not look for agents with a director role.
     * @return call controller
     */
    @Override
    public synchronized Employee searchAvailableAgent() {
        for (Employee employee : listAgent) {
            if(!employee.isBusy() && employee.getRole() == Roles.Operator){
              return employee;  
            }
            if(!employee.isBusy() && employee.getRole() == Roles.Supervisor){
               return employee;
            }
            if(!employee.isBusy() && employee.getRole() == Roles.Director){
              return employee;  
            }
        }
        return null;
    }

    /**
     * Method that updates the agent's status to available or busy
     * @param agent
     * @param status 
     */
    @Override
    public synchronized void updateStatusEmployee(Employee agent, Boolean status) {
        listAgent.parallelStream().forEach(k -> {
            agent.setBusy(status);
            if (k.equals(agent)) {
                k = agent;
            }
        });
    }

}
