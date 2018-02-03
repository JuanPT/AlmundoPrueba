package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.employee.Employee;

public interface IEmployeeControl {
    
    void register(Employee agent);
    
    Employee searchAvailableAgent();
    
    void updateStatusEmployee(Employee agent,Boolean status);
}
