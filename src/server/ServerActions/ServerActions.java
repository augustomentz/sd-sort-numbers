package server.ServerActions;

import worker.Worker;
import worker.WorkerActions.WorkerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerActions extends Remote {
    String checkConnection() throws RemoteException;
    void registerWorker(WorkerActions worker) throws RemoteException;
    void unregisterWorker(WorkerActions worker) throws RemoteException;
}
