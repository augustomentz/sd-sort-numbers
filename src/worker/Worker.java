package worker;

import server.ServerActions.ServerActions;
import worker.WorkerActions.WorkerActionsImpl;

import java.rmi.Naming;

public class Worker {
    ServerActions serverActions;

    public Worker() {
        connect();
    }

    public void connect () {
        try {
            serverActions = (ServerActions) Naming.lookup("rmi://localhost:1903/sort");

            WorkerActionsImpl workerActions = new WorkerActionsImpl();

            serverActions.registerWorker(workerActions);

            System.out.println("=======================");
            System.out.println("====== WORKER UP ======");
            System.out.println("=======================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Worker worker = new Worker();
    }
}
