package server.ServerActions;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerActionsImpl extends UnicastRemoteObject implements ServerActions {
    private final ArrayList<int[]> unsortedList;

    public ServerActionsImpl(ArrayList<int[]> unsortedList) throws RemoteException {
        super();

        this.unsortedList = unsortedList;
    }

    @Override
    public int[] requestDemand() throws RemoteException {
        int[] demand = this.unsortedList.get(0);
        this.unsortedList.remove(0);

        return demand;
    }

    @Override
    public Boolean haveDemand() {
        return this.unsortedList.size() > 0;
    }

    @Override
    public void notifyArraySorted(String workerId, int[] arraySorted) throws RemoteException {
        int[] shortArray = new int[20];

        System.arraycopy(arraySorted, 0, shortArray, 0, 20);

        System.out.println("Worker#" + workerId + " ordenou: " + Arrays.toString(shortArray));
    }
}
