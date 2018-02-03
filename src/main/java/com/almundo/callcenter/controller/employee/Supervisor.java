package com.almundo.callcenter.controller.employee;


public class Supervisor extends Employee{

    public Supervisor(String name, boolean occupied) {
        super(name, occupied);
        role = Roles.Supervisor;
    }

    
    
}
