Ejercicio de Java

El objetivo de este ejercicio es conocer cómo los candidatos a entrar a almundo.com usan herramientas básicas de Java y diseñan soluciones orientadas a objetos. Forma de entrega La solución tiene que estar pusheada en un repo git. La url del mismo tiene que ser enviada por email. Como alternativa a git, la solución puede ser adjuntada (.tar, .zip, etc). Consigna Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director. Requerimientos Diseñar el modelado de clases y diagramas UML necesarios para documentar y comunicar el diseño. Debe existir una clase Dispatcher encargada de manejar las llamadas, y debe contener el método dispatchCall para que las asigne a los empleados disponibles. La clase Dispatcher debe tener la capacidad de poder procesar 10 llamadas al mismo tiempo (de modo concurrente). Cada llamada puede durar un tiempo aleatorio entre 5 y 10 segundos. Debe tener un test unitario donde lleguen 10 llamadas. Extras/Plus Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre. Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes. Agregar los tests unitarios que se crean convenientes. Agregar documentación de código. Tener en Cuenta El proyecto debe ser creado con Maven. De ser necesario, anexar un documento con la explicación del cómo y porqué resolvió los puntos extras, o comentarlo en las clases donde se encuentran sus tests unitarios respectivos.

Solucion

Se resolvió el tema de de cuando no hay un empleado libre, implementando un cola que se encarga de encolar las llamadas cuando no hay un agente disponible y este se encarga de esperar hasta que el primer empleado que se libere tome la llamada en espera. Lo resolví por que es un punto critico ya que si no podrían perderse llamadas al no encontrar agentes disponibles.

Cuando llegan mas de 10 llamadas concurrentes, se encolan las llamadas hasta que un hilo pueda ejecutarla, básicamente el Executors.newFixedThreadPool(10) define la cantidad máxima de concurrencia a ejecutar en nuestro caso es 10, cuando el detecta que un hilo se puesto disponible tome el hilo y continua con la llamada.

Se realizaron diferentes test dejando una cobertura total del 84.92, la cual puede ser verificada a través del plugin de maven el cual fue agregado en el archivo pom.xml. (Adjunto imagen de la cobertura alcanzada)

Hay un clase start la cual es un main con el cual se puede ejecutar el proyecto.



Resultado Ejecucion para 10 llamadas y 10 Empleados disponibles.

cd C:\AlmundoCallCenter; "JAVA_HOME=C:\\Program Files\\Java\\jdk1.8.0_121" M2_HOME=C:\\maven cmd /c "\"\"C:\\maven\\bin\\mvn.cmd\" -Dexec.args=\"-classpath %classpath com.almundo.callcenter.start\" -Dexec.executable=java -Dexec.classpathScope=runtime -Dmaven.ext.class.path=\"C:\\Program Files\\NetBeans 8.2\\java\\maven-nblib\\netbeans-eventspy.jar\" -Dfile.encoding=UTF-8 org.codehaus.mojo:exec-maven-plugin:1.2.1:exec -Dcurrent.jrebel.agent.path=C:\\Users\\JUANPA~1\\AppData\\Local\\Temp\\.jr1615CA8CD4A\\jrebel.jar\""
Running NetBeans Compile On Save execution. Phase execution is skipped and output directories of dependency projects (with Compile on Save turned on) will be used instead of their jar artifacts.
Scanning for projects...

------------------------------------------------------------------------
Building CallCenter 1.0-SNAPSHOT
------------------------------------------------------------------------

--- exec-maven-plugin:1.2.1:exec (default-cli) @ CallCenter ---
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.324  /*****El agente Juan Pablo Toro ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.327  /*****El agente Oscar perez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.332  /*****El agente Marcela barrera ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.333  /*****El agente Lucas ramirez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.333  /*****El agente Jenifer monsalve ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.332  /*****El agente Luz elvira vasques ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.327  /*****El agente Johan garcia ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.332  /*****El agente Evans valyes ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.333  /*****El agente Mauricio Restrepo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:05:54 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:05:54.324  /*****El agente Daniel giraldo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Jenifer monsalve ha finalizado la llamada con duracion 5.663 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 8 inicio 2018-02-03 15:05:54.333 fin 2018-02-03 15:06:00.61 con duracion 5663 fue contestado por el empleado Jenifer monsalve con rol Operator llamadas en cola 0
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Juan Pablo Toro ha finalizado la llamada con duracion 5.681 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 1 inicio 2018-02-03 15:05:54.324 fin 2018-02-03 15:06:00.84 con duracion 5681 fue contestado por el empleado Juan Pablo Toro con rol Operator llamadas en cola 0
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Johan garcia ha finalizado la llamada con duracion 6.412 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:00 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 3 inicio 2018-02-03 15:05:54.327 fin 2018-02-03 15:06:00.812 con duracion 6412 fue contestado por el empleado Johan garcia con rol Operator llamadas en cola 0
feb 03, 2018 3:06:01 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Daniel giraldo ha finalizado la llamada con duracion 6.767 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:01 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 2 inicio 2018-02-03 15:05:54.324 fin 2018-02-03 15:06:01.169 con duracion 6767 fue contestado por el empleado Daniel giraldo con rol Operator llamadas en cola 0
feb 03, 2018 3:06:01 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Mauricio Restrepo ha finalizado la llamada con duracion 7.524 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:01 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 9 inicio 2018-02-03 15:05:54.333 fin 2018-02-03 15:06:01.925 con duracion 7524 fue contestado por el empleado Mauricio Restrepo con rol Operator llamadas en cola 0
feb 03, 2018 3:06:02 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Lucas ramirez ha finalizado la llamada con duracion 8.493 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:02 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 10 inicio 2018-02-03 15:05:54.333 fin 2018-02-03 15:06:02.891 con duracion 8493 fue contestado por el empleado Lucas ramirez con rol Operator llamadas en cola 0
feb 03, 2018 3:06:02 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Luz elvira vasques ha finalizado la llamada con duracion 8.493 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:02 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 5 inicio 2018-02-03 15:05:54.332 fin 2018-02-03 15:06:02.892 con duracion 8493 fue contestado por el empleado Luz elvira vasques con rol Director llamadas en cola 0
feb 03, 2018 3:06:03 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Marcela barrera ha finalizado la llamada con duracion 8.912 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:03 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 7 inicio 2018-02-03 15:05:54.332 fin 2018-02-03 15:06:03.308 con duracion 8912 fue contestado por el empleado Marcela barrera con rol Supervisor llamadas en cola 0
feb 03, 2018 3:06:03 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Oscar perez ha finalizado la llamada con duracion 9.205 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:03 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 4 inicio 2018-02-03 15:05:54.327 fin 2018-02-03 15:06:03.600 con duracion 9205 fue contestado por el empleado Oscar perez con rol Operator llamadas en cola 0
feb 03, 2018 3:06:04 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Evans valyes ha finalizado la llamada con duracion 9.859 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:06:04 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 6 inicio 2018-02-03 15:05:54.332 fin 2018-02-03 15:06:04.259 con duracion 9859 fue contestado por el empleado Evans valyes con rol Supervisor llamadas en cola 0
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time: 11.160 s
Finished at: 2018-02-03T15:06:04-05:00
Final Memory: 6M/184M
------------------------------------------------------------------------



Resultado para 10 llamadas y 9 empleados disponibles.

cd C:\AlmundoCallCenter; "JAVA_HOME=C:\\Program Files\\Java\\jdk1.8.0_121" M2_HOME=C:\\maven cmd /c "\"\"C:\\maven\\bin\\mvn.cmd\" -Dexec.args=\"-classpath %classpath com.almundo.callcenter.start\" -Dexec.executable=java -Dexec.classpathScope=runtime -Dmaven.ext.class.path=\"C:\\Program Files\\NetBeans 8.2\\java\\maven-nblib\\netbeans-eventspy.jar\" -Dfile.encoding=UTF-8 org.codehaus.mojo:exec-maven-plugin:1.2.1:exec -Dcurrent.jrebel.agent.path=C:\\Users\\JUANPA~1\\AppData\\Local\\Temp\\.jr1615CA8CD4A\\jrebel.jar\""
Running NetBeans Compile On Save execution. Phase execution is skipped and output directories of dependency projects (with Compile on Save turned on) will be used instead of their jar artifacts.
Scanning for projects...

------------------------------------------------------------------------
Building CallCenter 1.0-SNAPSHOT
------------------------------------------------------------------------

--- exec-maven-plugin:1.2.1:exec (default-cli) @ CallCenter ---
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.QueueCall update
INFO: /*****Se ha encolado una llamada, hay 1 en cola con id 10con estado WAITING
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Johan garcia ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Daniel giraldo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Luz elvira vasques ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Evans valyes ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.894  /*****El agente Marcela barrera ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Oscar perez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.894  /*****El agente Mauricio Restrepo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.893  /*****El agente Juan Pablo Toro ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:38 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:38.894  /*****El agente Jenifer monsalve ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:07:46.554  /*****El agente Mauricio Restrepo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Mauricio Restrepo ha finalizado la llamada con duracion 7.602 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 9 inicio 2018-02-03 15:07:38.894 fin 2018-02-03 15:07:46.554 con duracion 7602 fue contestado por el empleado Mauricio Restrepo con rol Operator llamadas en cola 1
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Jenifer monsalve ha finalizado la llamada con duracion 7.612 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 8 inicio 2018-02-03 15:07:38.894 fin 2018-02-03 15:07:46.569 con duracion 7612 fue contestado por el empleado Jenifer monsalve con rol Operator llamadas en cola 0
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Daniel giraldo ha finalizado la llamada con duracion 7.69 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:46 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 2 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:46.637 con duracion 7690 fue contestado por el empleado Daniel giraldo con rol Operator llamadas en cola 0
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Johan garcia ha finalizado la llamada con duracion 8.431 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 3 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:47.377 con duracion 8431 fue contestado por el empleado Johan garcia con rol Operator llamadas en cola 0
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Marcela barrera ha finalizado la llamada con duracion 8.51 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 7 inicio 2018-02-03 15:07:38.894 fin 2018-02-03 15:07:47.460 con duracion 8510 fue contestado por el empleado Marcela barrera con rol Supervisor llamadas en cola 0
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Juan Pablo Toro ha finalizado la llamada con duracion 8.576 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:47 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 1 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:47.532 con duracion 8576 fue contestado por el empleado Juan Pablo Toro con rol Operator llamadas en cola 0
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Luz elvira vasques ha finalizado la llamada con duracion 9.462 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 5 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:48.410 con duracion 9462 fue contestado por el empleado Luz elvira vasques con rol Director llamadas en cola 0
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Oscar perez ha finalizado la llamada con duracion 9.876 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 4 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:48.827 con duracion 9876 fue contestado por el empleado Oscar perez con rol Operator llamadas en cola 0
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Evans valyes ha finalizado la llamada con duracion 9.961 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:48 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 6 inicio 2018-02-03 15:07:38.893 fin 2018-02-03 15:07:48.910 con duracion 9961 fue contestado por el empleado Evans valyes con rol Supervisor llamadas en cola 0
feb 03, 2018 3:07:53 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Mauricio Restrepo ha finalizado la llamada con duracion 7.042 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:07:53 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 10 inicio 2018-02-03 15:07:46.554 fin 2018-02-03 15:07:53.597 con duracion 7042 fue contestado por el empleado Mauricio Restrepo con rol Operator llamadas en cola 0
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time: 15.926 s
Finished at: 2018-02-03T15:07:53-05:00
Final Memory: 6M/184M
------------------------------------------------------------------------



Resultado para 11 llamadas concurrentes.


cd C:\AlmundoCallCenter; "JAVA_HOME=C:\\Program Files\\Java\\jdk1.8.0_121" M2_HOME=C:\\maven cmd /c "\"\"C:\\maven\\bin\\mvn.cmd\" -Dexec.args=\"-classpath %classpath com.almundo.callcenter.start\" -Dexec.executable=java -Dexec.classpathScope=runtime -Dmaven.ext.class.path=\"C:\\Program Files\\NetBeans 8.2\\java\\maven-nblib\\netbeans-eventspy.jar\" -Dfile.encoding=UTF-8 org.codehaus.mojo:exec-maven-plugin:1.2.1:exec -Dcurrent.jrebel.agent.path=C:\\Users\\JUANPA~1\\AppData\\Local\\Temp\\.jr1615CA8CD4A\\jrebel.jar\""
Running NetBeans Compile On Save execution. Phase execution is skipped and output directories of dependency projects (with Compile on Save turned on) will be used instead of their jar artifacts.
Scanning for projects...

------------------------------------------------------------------------
Building CallCenter 1.0-SNAPSHOT
------------------------------------------------------------------------

--- exec-maven-plugin:1.2.1:exec (default-cli) @ CallCenter ---
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.QueueCall update
INFO: /*****Se ha encolado una llamada, hay 1 en cola con id 10con estado WAITING
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Johan garcia ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.460  /*****El agente Jenifer monsalve ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.460  /*****El agente Marcela barrera ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.460  /*****El agente Lucas ramirez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.460  /*****El agente Mauricio Restrepo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Oscar perez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Luz elvira vasques ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Daniel giraldo ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Juan Pablo Toro ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:17 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:17.459  /*****El agente Evans valyes ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:22 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: 2018-02-03 15:09:22.653  /*****El agente Oscar perez ha contestado la llamada y se encuentra en estado ocupado ANSWERED
feb 03, 2018 3:09:22 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Oscar perez ha finalizado la llamada con duracion 5.143 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:22 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 4 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:22.653 con duracion 5143 fue contestado por el empleado Oscar perez con rol Operator llamadas en cola 1
feb 03, 2018 3:09:22 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Daniel giraldo ha finalizado la llamada con duracion 5.284 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:22 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 2 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:22.799 con duracion 5284 fue contestado por el empleado Daniel giraldo con rol Operator llamadas en cola 0
feb 03, 2018 3:09:23 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Luz elvira vasques ha finalizado la llamada con duracion 5.855 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:23 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 6 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:23.370 con duracion 5855 fue contestado por el empleado Luz elvira vasques con rol Director llamadas en cola 0
feb 03, 2018 3:09:24 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Evans valyes ha finalizado la llamada con duracion 6.744 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:24 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 5 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:24.262 con duracion 6744 fue contestado por el empleado Evans valyes con rol Supervisor llamadas en cola 0
feb 03, 2018 3:09:25 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Jenifer monsalve ha finalizado la llamada con duracion 8.202 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:25 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 7 inicio 2018-02-03 15:09:17.460 fin 2018-02-03 15:09:25.708 con duracion 8202 fue contestado por el empleado Jenifer monsalve con rol Operator llamadas en cola 0
feb 03, 2018 3:09:25 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Lucas ramirez ha finalizado la llamada con duracion 8.29 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:25 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 11 inicio 2018-02-03 15:09:17.460 fin 2018-02-03 15:09:25.798 con duracion 8290 fue contestado por el empleado Lucas ramirez con rol Operator llamadas en cola 0
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Juan Pablo Toro ha finalizado la llamada con duracion 9.552 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 1 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:27.69 con duracion 9552 fue contestado por el empleado Juan Pablo Toro con rol Operator llamadas en cola 0
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Marcela barrera ha finalizado la llamada con duracion 9.781 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 8 inicio 2018-02-03 15:09:17.460 fin 2018-02-03 15:09:27.289 con duracion 9781 fue contestado por el empleado Marcela barrera con rol Supervisor llamadas en cola 0
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Johan garcia ha finalizado la llamada con duracion 9.864 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 3 inicio 2018-02-03 15:09:17.459 fin 2018-02-03 15:09:27.369 con duracion 9864 fue contestado por el empleado Johan garcia con rol Operator llamadas en cola 0
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Mauricio Restrepo ha finalizado la llamada con duracion 9.925 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:27 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 9 inicio 2018-02-03 15:09:17.460 fin 2018-02-03 15:09:27.434 con duracion 9925 fue contestado por el empleado Mauricio Restrepo con rol Operator llamadas en cola 0
feb 03, 2018 3:09:31 PM com.almundo.callcenter.controller.EmployeeControl update
INFO: /*****El agente Oscar perez ha finalizado la llamada con duracion 8.945 Seg y se encuentra disponible para recibir llamadas 
feb 03, 2018 3:09:31 PM com.almundo.callcenter.controller.Dispatcher finishCall
INFO: La llamada con el identificador 10 inicio 2018-02-03 15:09:22.653 fin 2018-02-03 15:09:31.600 con duracion 8945 fue contestado por el empleado Oscar perez con rol Operator llamadas en cola 0
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time: 15.138 s
Finished at: 2018-02-03T15:09:31-05:00
Final Memory: 6M/184M
------------------------------------------------------------------------


