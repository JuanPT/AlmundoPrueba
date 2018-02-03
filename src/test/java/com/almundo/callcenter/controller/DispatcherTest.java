package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.call.Status;
import com.almundo.callcenter.controller.employee.Employee;
import com.almundo.callcenter.controller.employee.Operator;
import com.almundo.callcenter.controller.util.Util;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;



public class DispatcherTest {
    
    @Test
    public void testDispatchCallWhenAgentIsNotNullAndCallIsNotNullInvokeCalls() {
        Call call = new Call("call : 1", Util.generateRandom());
        call.setStateCall(Status.FINISHED);
        Employee employee = null;
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).calls(call,employee);
        dispatcher.dispatchCall(call);
        Mockito.verify(dispatcher, times(1)).calls(call, employee);
    }

    @Test
    public void testDispatchCallWhenAgentIsNotNullAndCallIsNotNullNotInvoke() {
        Call call = null;
        Employee employee = null;
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).calls(call, employee);
        dispatcher.dispatchCall(call);
        Mockito.verify(dispatcher, times(0)).calls(call, employee);
    }

    @Test
    public void testCallsWhenCallIsAgentBusyInvokeCallWaitingOneTime() {
        Call call = new Call("Call:1", Util.generateRandom());
        call.setStateCall(Status.FINISHED);
        Employee employee = new Employee("Juan pablo", true);
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).callWaiting(call);
        dispatcher.calls(call, employee);
        Mockito.verify(dispatcher, Mockito.times(1)).callWaiting(call);
    }

    @Test
    public void testCallsWhenCallIsAgentBusyInvokeFinishCallOneTime() {
        Call call = new Call("Call:1", Util.generateRandom());
        Employee employee = new Employee("Juan pablo", false);
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).finishCall(call);
        dispatcher.calls(call, employee);
        Mockito.verify(dispatcher, Mockito.times(1)).finishCall(call);
    }

    @Test
    public void testFinishCallReturnCallFinished() {
        Call call = new Call("Call:2", Util.generateRandom());
        call.setStateCall(Status.ANSWERED);
        Employee agent = new Employee("Daniel torres", true);
        call.setAgent(agent);
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).notifyObservers(Mockito.any());
        dispatcher.finishCall(call);
        Assert.assertEquals(call.getStateCall(), Status.FINISHED);
    }

    @Test
    public void testCallWaitingReturnCallWaiting() {
        Call call = new Call("Call:2", Util.generateRandom());
        call.setStateCall(Status.NEW);
        Employee agent = new Employee("Daniel torres", true);
        call.setAgent(agent);
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        Mockito.doNothing().when(dispatcher).notifyObservers(Mockito.any());
        Mockito.doNothing().when(dispatcher).waitAgentAvailable();
        dispatcher.callWaiting(call);
        Assert.assertEquals(call.getStateCall(), Status.WAITING);
    }

    @Test
    public void tesWaitAgentAvailableWhenAgentNotNullReturnQueueTwoElements() {
        EmployeeControl employeeControl = EmployeeControl.getInstance();
        employeeControl.register(new Operator("Juan Pablo Toro", false));
        Call call = new Call("Call:2", Util.generateRandom());
        call.setStateCall(Status.NEW);
        Call callTwo = new Call("Call:2", Util.generateRandom());
        call.setStateCall(Status.NEW);
        QueueCall queueCall = Mockito.spy(QueueCall.getInstance());
        queueCall.put(call);
        queueCall.put(callTwo);
        Dispatcher dispatcher = Mockito.spy(new Dispatcher(call));
        dispatcher.waitAgentAvailable();
        Assert.assertEquals(1, queueCall.queue.size());
    }

}
