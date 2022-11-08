package worker.WorkerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WorkerActions extends Remote {
    Boolean isBusy() throws RemoteException;
    int[] sort(int[] numbers) throws RemoteException;
}
