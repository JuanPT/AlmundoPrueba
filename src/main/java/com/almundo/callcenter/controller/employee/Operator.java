package com.almundo.callcenter.controller.employee;


public class Operator extends Employee{

    public Operator(String name,boolean occupied) {
        super(name, occupied);
        role = Roles.Operator;
    }
    
}
