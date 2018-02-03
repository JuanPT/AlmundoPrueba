package com.almundo.callcenter.controller.call;

import com.almundo.callcenter.controller.employee.Employee;


public class Call {
    
    private String id;
    private Status stateCall;
    private long durationSeg;
    private Employee agent;
    private int callQueue;
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Call(String id, long durationSeg) {
        this.id = id;
        this.stateCall = Status.NEW;
        this.durationSeg = durationSeg;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStateCall() {
        return stateCall;
    }

    public void setStateCall(Status stateCall) {
        this.stateCall = stateCall;
    }

    public long getDurationSeg() {
        return durationSeg;
    }

    public void setDurationSeg(long durationSeg) {
        this.durationSeg = durationSeg;
    }

    public int getCallQueue() {
        return callQueue;
    }

    public void setCallQueue(int callQueue) {
        this.callQueue = callQueue;
    }

    public Employee getAgent() {
        return agent;
    }

    public void setAgent(Employee agent) {
        this.agent = agent;
    }

    
    
}
