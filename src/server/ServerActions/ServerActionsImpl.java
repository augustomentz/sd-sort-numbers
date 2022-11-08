package server.ServerActions;

import worker.Worker;
import worker.WorkerActions.WorkerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerActionsImpl extends UnicastRemoteObject implements ServerActions {
    String id;
    List<WorkerActions> workers = new ArrayList<>();

    public ServerActionsImpl() throws RemoteException {
        super();
    }

    @Override
    public String checkConnection() throws RemoteException {
        return "Connection is established";
    }

    @Override
    public void registerWorker(WorkerActions worker) throws RemoteException {
        this.workers.add(worker);
    }

    @Override
    public void unregisterWorker(WorkerActions worker) throws RemoteException {
        this.workers.remove(worker);
    }

    public void startProcessing(ArrayList<int[]> numbers) {
        try {
            while(!numbers.isEmpty()) {
                for (WorkerActions worker : workers) {
                    if (!worker.isBusy()) {
                        int[] vet = numbers.get(0);
                        numbers.remove(0);

                        System.out.println(Arrays.toString(worker.sort(vet)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
