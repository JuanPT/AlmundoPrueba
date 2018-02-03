package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import com.almundo.callcenter.controller.employee.Director;
import com.almundo.callcenter.controller.employee.Employee;
import com.almundo.callcenter.controller.employee.Operator;
import com.almundo.callcenter.controller.employee.Roles;
import com.almundo.callcenter.controller.employee.Supervisor;
import java.util.ArrayList;
import java.util.Observable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class EmployeeControlTest {
    
    @Test
    public void testSearchAvailableAgentWhenThereAreAgentWithRolOperativeReturnAgent(){
        EmployeeControl d = EmployeeControl.getInstance();
        d.listAgent = new ArrayList<>();
        d.register(new Operator("Juan Pablo Toro", false));
        
        Employee employee = d.searchAvailableAgent();
        Assert.assertEquals(Roles.Operator,employee.getRole());
    }
    
    @Test
    public void testSearchAvailableAgentWhenThereAreAgentWithRolOperativeReturnSupervisor(){
        EmployeeControl d = EmployeeControl.getInstance();
        d.listAgent = new ArrayList<>();
        d.register(new Operator("Juan Pablo Toro", true));
        d.register(new Supervisor("Evans valyes", false));
        
        Employee employee = d.searchAvailableAgent();
        Assert.assertEquals(Roles.Supervisor,employee.getRole());
    }
    
    @Test
    public void testSearchAvailableAgentWhenThereAreAgentWithRolOperativeReturnDirector(){
        EmployeeControl d = EmployeeControl.getInstance();
        d.listAgent = new ArrayList<>();
        d.register(new Operator("Juan Pablo Toro", true));
        d.register(new Supervisor("Evans valyes", true));
        d.register(new Director("Luz elvira vasques", false));
        
        Employee employee = d.searchAvailableAgent();
        Assert.assertEquals(Roles.Director,employee.getRole());
    }
    
    @Test
    public void testUpdateStatusEmployeeWhenStatusIsFalseReturnEmployeeStatusFalse(){
        EmployeeControl d = EmployeeControl.getInstance();
        d.register(new Operator("Juan Pablo Toro", true));
        
        Employee employee = d.listAgent.get(0);
        d.updateStatusEmployee(employee, Boolean.FALSE);
        Assert.assertFalse(d.listAgent.get(0).isBusy());
    }
    
    @Test
    public void testUpdateStatusEmployeeWhenStatusIsTrueReturnEmployeeStatusTrue(){
        EmployeeControl d = EmployeeControl.getInstance();
        d.register(new Operator("Juan Pablo Toro", false));
        
        Employee employee = d.listAgent.get(0);
        d.updateStatusEmployee(employee, Boolean.TRUE);
        Assert.assertTrue(d.listAgent.get(0).isBusy());
    }
    
    @Test
    public void testUpdateWhenCallIsAnsweredInvokeUpdateStatusEmployee(){
       EmployeeControl d = Mockito.spy(EmployeeControl.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.ANSWERED);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(d).updateStatusEmployee(Mockito.any(), Mockito.any());
       d.update(new Observable(),args);
       Mockito.verify(d,Mockito.times(1)).updateStatusEmployee(Mockito.any(), Mockito.any());
    }
    
    @Test
    public void testUpdateWhenCallIsFinishedInvokeUpdateStatusEmployee(){
       EmployeeControl d = Mockito.spy(EmployeeControl.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.FINISHED);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(d).updateStatusEmployee(Mockito.any(), Mockito.any());
       d.update(new Observable(),args);
       Mockito.verify(d,Mockito.times(1)).updateStatusEmployee(Mockito.any(), Mockito.any());
    }
    
    @Test
    public void testUpdateWhenCallIsWaitingNotInvokeUpdateStatusEmployee(){
       EmployeeControl d = Mockito.spy(EmployeeControl.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.WAITING);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(d).updateStatusEmployee(Mockito.any(), Mockito.any());
       d.update(new Observable(),args);
       Mockito.verify(d,Mockito.times(0)).updateStatusEmployee(Mockito.any(), Mockito.any());
    }
    
    @Test
    public void testUpdateWhenCallIsNewNotInvokeUpdateStatusEmployee(){
       EmployeeControl d = Mockito.spy(EmployeeControl.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.NEW);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(d).updateStatusEmployee(Mockito.any(), Mockito.any());
       d.update(new Observable(),args);
       Mockito.verify(d,Mockito.times(0)).updateStatusEmployee(Mockito.any(), Mockito.any());
    }
    
    
}
