package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import com.almundo.callcenter.controller.employee.Employee;
import com.almundo.callcenter.controller.employee.Roles;
import java.util.Observable;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class QueueCallTest {
    
    @Test
    public void testUpdateWhenCallIsWaitingInvokeAddCallToTheQueue(){
       QueueCall queueCall = Mockito.spy(QueueCall.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.WAITING);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(queueCall).addCallToTheQueue(call);
       queueCall.update(new Observable(),args);
       Mockito.verify(queueCall,Mockito.times(1)).addCallToTheQueue(Mockito.any());
    }
    
    @Test
    public void testUpdateWhenCallIsNewNotInvokeAddCallToTheQueue(){
       QueueCall queueCall = Mockito.spy(QueueCall.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.NEW);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(queueCall).addCallToTheQueue(call);
       queueCall.update(new Observable(),args);
       Mockito.verify(queueCall,Mockito.times(0)).addCallToTheQueue(Mockito.any());
    }
    
    @Test
    public void testUpdateWhenCallIsFinishedNotInvokeAddCallToTheQueue(){
       QueueCall queueCall = Mockito.spy(QueueCall.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.FINISHED);
       call.setAgent(employee);
       Object args = call;
       Mockito.doNothing().when(queueCall).addCallToTheQueue(call);
       queueCall.update(new Observable(),args);
       Mockito.verify(queueCall,Mockito.times(0)).addCallToTheQueue(Mockito.any());
    }
    
    @Test
    public void testAddCallToTheQueueWhenCallIsNotNullReturnCountQueueOne(){
       QueueCall queueCall = QueueCall.getInstance();
       Employee employee = new Employee("Daniel", true);
       employee.setName("Daniel");
       employee.setRole(Roles.Operator);
       Call call = new Call(String.valueOf(1),5697);
       call.setStateCall(Status.FINISHED);
       call.setAgent(employee);
       call.setDurationSeg(5697);
       call.setId(String.valueOf(1));
       
       queueCall.addCallToTheQueue(call);
        Assert.assertEquals(1,queueCall.queue.size());
    }
    
    @Test
    public void testAddCallToTheQueueWhenCallIsNullReturnCountQueueZero(){
       QueueCall queueCall = QueueCall.getInstance();
       queueCall.queue = new LinkedBlockingQueue<>();
       Call call = null;
       
       queueCall.addCallToTheQueue(call);
       Assert.assertEquals(0,queueCall.queue.size());
    }
    
    @Test
    public void testAddCallToTheQueueWhenIsNullCallNotInvokePut(){
       QueueCall queueCall = Mockito.spy(QueueCall.getInstance());
       Employee employee = new Employee("Daniel", true);
       Call call = null;
       Mockito.doNothing().when(queueCall).put(call);
       queueCall.addCallToTheQueue(call);
       Mockito.verify(queueCall,Mockito.times(0)).put(Mockito.any());
    }
    
    
}
