package com.almundo.callcenter.controller.employee;


public class Person {
    
    private String name;
    
    private boolean busy;
   

    public Person(String name,boolean busy) {
        this.name = name;
        this.busy = busy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    

}
