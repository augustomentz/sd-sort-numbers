package worker;

import server.ServerActions.ServerActions;
import worker.WorkerActions.WorkerActionsImpl;

import java.rmi.Naming;
import java.util.UUID;

public class Worker {
    String id;
    ServerActions serverActions;

    public Worker() {
        this.id = UUID.randomUUID().toString().substring(0, 6);

        connect();
    }

    public void connect () {
        try {
            serverActions = (ServerActions) Naming.lookup("rmi://localhost:1903/sort");

            WorkerActionsImpl workerActions = new WorkerActionsImpl(this.id);

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
