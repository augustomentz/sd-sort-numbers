package worker.WorkerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class WorkerActionsImpl extends UnicastRemoteObject implements WorkerActions {
    String id;

    public WorkerActionsImpl() throws RemoteException {
        super();

        this.id = UUID.randomUUID().toString();
    }

    public int[] sort(int[] numbers) {
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

        return numbers;
    }
}
