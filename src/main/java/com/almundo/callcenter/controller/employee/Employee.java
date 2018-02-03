package com.almundo.callcenter.controller.employee;


public class Employee extends Person{
    protected Roles role;
    
    public Employee(String name, boolean busy) {
        super(name, busy);
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    
    
    
}
