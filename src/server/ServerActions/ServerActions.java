package server.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerActions extends Remote {
    int[] requestDemand() throws RemoteException;
    Boolean haveDemand() throws  RemoteException;
    void notifyArraySorted(String workerId, int[] arraySorted) throws RemoteException;
}
