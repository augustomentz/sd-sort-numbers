package worker;

import server.ServerActions.ServerActions;

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

            System.out.println("=======================");
            System.out.println("====== WORKER UP ======");
            System.out.println("=======================\n");

            while(serverActions.haveDemand()) {
                serverActions.notifyArraySorted(this.id, this.sort(serverActions.requestDemand()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] sort(int[] numbers) {
        System.out.println("Worker#" + this.id + " está ordenando");

        int aux = 0;

        for(int i = 0; i < numbers.length - 1; i++){
            for(int j = 0; j < numbers.length - 1; j++){
                if (numbers[j] > numbers[j + 1]) {
                    aux = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = aux;
                }
            }
        }

        return numbers;
    }

    public static void main(String args[]) {
        Worker worker = new Worker();
    }
}
