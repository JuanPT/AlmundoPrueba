package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Status;

public class EventCall {
    
    private String id;
    private Status stateCall;
    
    private long durationSeg;

    public EventCall(String id, long durationSeg) {
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
}
