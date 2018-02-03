package com.almundo.callcenter.controller.employee;


public class Director extends Employee{

    public Director(String name, boolean occupied) {
        super(name, occupied);
        role = Roles.Director;
    }
    
}
