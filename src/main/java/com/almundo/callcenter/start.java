package com.almundo.callcenter;

import com.almundo.callcenter.controller.Dispatcher;
import com.almundo.callcenter.controller.EmployeeControl;
import com.almundo.callcenter.controller.QueueCall;
import com.almundo.callcenter.controller.employee.Director;
import com.almundo.callcenter.controller.employee.Operator;
import com.almundo.callcenter.controller.employee.Supervisor;
import com.almundo.callcenter.controller.call.Call;
import com.almundo.callcenter.controller.util.Util;

public class start {

    public static void main(String[] args) throws InterruptedException {
        registerEmployees();
        EmployeeControl observador = EmployeeControl.getInstance();
        QueueCall queueCall = QueueCall.getInstance();
        for (int i = 1; i < 11; i++) {
            Dispatcher callObserved = new Dispatcher(new Call(String.valueOf(i), Util.generateRandom()));
            callObserved.addObserver(observador);
            callObserved.addObserver(queueCall);
            callObserved.startCallProcess();
        }
    }

    public static void registerEmployees() {
        EmployeeControl d = EmployeeControl.getInstance();
        d.register(new Operator("Juan Pablo Toro", false));
        d.register(new Operator("Daniel giraldo", false));
        d.register(new Operator("Johan garcia", false));
        d.register(new Operator("Oscar perez", false));
        d.register(new Director("Luz elvira vasques", false));
        d.register(new Supervisor("Evans valyes", false));
        d.register(new Supervisor("Marcela barrera", false));
        d.register(new Operator("Jenifer monsalve", false));
        d.register(new Operator("Mauricio Restrepo", false));
        d.register(new Operator("Lucas ramirez", false));
    }

}
