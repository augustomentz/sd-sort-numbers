package worker.WorkerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerActionsImpl extends UnicastRemoteObject implements WorkerActions {
    String id;
    private Boolean busy = false;

    public WorkerActionsImpl(String id) throws RemoteException {
        super();

        this.id = id;
    }

    public int[] sort(int[] numbers) {
        this.setBusy(true);
        System.out.println(this.id + " está ocupado\n");

        int aux = 0;

        for(int i = 0; i < numbers.length - 1; i++){
            for(int j = 0; j < numbers.length - 1; j++){
                if(numbers[j] > numbers[j + 1]){
                    aux = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = aux;
                }
            }
        }

        this.setBusy(false);

        return numbers;
    }

    public Boolean isBusy() {
        return this.busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }
}
