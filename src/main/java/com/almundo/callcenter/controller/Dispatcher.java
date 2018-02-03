package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import com.almundo.callcenter.controller.employee.Employee;
import com.almundo.callcenter.controller.util.Util;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dispatcher extends Observable implements Runnable {

    public static final Logger LOG = Logger.getLogger(Dispatcher.class.getName());
    

    EmployeeControl employeeControl = EmployeeControl.getInstance();

    QueueCall queueCall = QueueCall.getInstance();

    Call callNew;
    
    ExecutorService executor = Executors.newFixedThreadPool(10);
    

    /**
     * Constructor
     * @param call 
     */
    public Dispatcher(Call call) {
        callNew = call;
    }

    /**
     *  
     */
    @Override
    public void run() {
        dispatchCall(callNew);
    }

    /**
     * 
     */
    public void startCallProcess() {
        executor.submit(this);
        executor.shutdown();
    }

    /**
     * method that receives the call and looks for an available agent
     *
     * @param call
     */
    protected void dispatchCall(Call call) {
        if (call != null) {
            Employee agent = employeeControl.searchAvailableAgent();
            calls(call, agent);
        }
    }

    /**
     * Method call is responsible for receiving the call and call controller,
     * this method verifies the status of the call to proceed to start it or put
     * it in the queue.
     *
     * @param call
     * @param agent
     */
    protected void calls(Call call, Employee agent) {
        if (agent != null && !agent.isBusy() && call.getStateCall() == Status.NEW) {
            call = startCall(agent, call);
            try {
                sleep(call.getDurationSeg());
                finishCall(call);
            } catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        } else {
            callWaiting(call);
        }
    }

    /**
     * Method that is responsible for initiating the call changes the state and
     * is responsible for notifying to perform the update of the status of the
     * employee assigned to the call.
     *
     * @param agent
     * @param call
     * @return 
     */
    protected Call startCall(Employee agent, Call call) {
        call.setStateCall(Status.ANSWERED);
        agent.setBusy(true);
        call.setAgent(agent);
        call.setStartDate(Util.dateFormater(new Date()));
        setChanged();
        notifyObservers(call);
        return call;
    }

    /**
     * End call method is responsible for terminating the call, it notifies to
     * update the status of the employee and make it available.
     *
     * @param call
     */
    protected void finishCall(Call call) {
        if (call.getStateCall() == Status.ANSWERED) {
            call.setStateCall(Status.FINISHED);
            call.setCallQueue(queueCall.queue.size());
            call.setEndDate(Util.dateFormater(new Date()));
            setChanged();
            notifyObservers(call);
            LOG.log(Level.INFO, getMessage(call));
        }

    }

    /**
     * method that is responsible for placing the call on hold and notifies the
     * queue to add it to the call queue.
     * @param call
     */
    protected void callWaiting(Call call) {
        call.setStateCall(Status.WAITING);
        setChanged();
        notifyObservers(call);
        waitAgentAvailable();
    }

    /**
     * Method that waits for an agent to be available
     */
    protected synchronized void waitAgentAvailable() {
        Employee agent = null;
        while (agent == null) {

            agent = employeeControl.searchAvailableAgent();

        }
        Call callQueue = queueCall.queue.remove();
        callQueue.setStateCall(Status.NEW);
        if (!agent.isBusy() && callQueue.getStateCall() == Status.NEW) {
            calls(callQueue, agent);
        }
    }

    /**
     * 
     * @param call
     * @return 
     */
    public String getMessage(Call call) {
        StringBuilder sql = new StringBuilder();
        sql.append("La llamada con el identificador ");
        sql.append(call.getId());
        sql.append(" inicio ");
        sql.append(call.getStartDate());
        sql.append(" fin ");
        sql.append(call.getEndDate());
        sql.append(" con duracion ");
        sql.append(call.getDurationSeg());
        sql.append(" fue contestado por el empleado ");
        sql.append(call.getAgent().getName());
        sql.append(" con rol ");
        sql.append(call.getAgent().getRole());
        sql.append(" llamadas en cola ");
        sql.append(call.getCallQueue());
        return sql.toString();
    }

}
