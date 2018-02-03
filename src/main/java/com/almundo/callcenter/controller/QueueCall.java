package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueCall implements IQueue, Observer {

    private static final Logger LOG = Logger.getLogger(QueueCall.class.getName());

    Queue<Call> queue;

    private static QueueCall INSTANCE;

    public QueueCall() {
        queue = new LinkedBlockingQueue<>();
    }

    /**
     * Static method that generates a single instance of the QueueCall class
     *
     * @return instance QueueCall
     */
    public static QueueCall getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QueueCall();
        }
        return INSTANCE;
    }

    /**
     * Method that adds a call to the queue
     *
     * @param object
     */
    @Override
    public void put(Object object) {
        queue.add((Call) object);
    }

    /**
     * Method that receives notifications by the call and according to the state
     * of the call allows queuing
     *
     * @param o
     * @param arg
     */
    @Override
    public synchronized void update(Observable o, Object arg) {
        Call call = ((Call) arg);
        if (call.getStateCall().equals(Status.WAITING)) {
            addCallToTheQueue(call);
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.INFO, "/*****Se ha encolado una llamada, hay {0} en cola con id " + call.getId() + "con estado " + call.getStateCall(), call.getCallQueue());
        }
    }

    /**
     * method that adds the call to the queue and sends how many calls are
     * queued
     *
     * @param call
     */
    protected synchronized void addCallToTheQueue(Call call) {
        if (call != null) {
            put(call);
            call.setCallQueue(queue.size());
        }

    }

}
