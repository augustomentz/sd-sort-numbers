package server;

import server.ServerActions.ServerActionsImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Server {
    public static int MAX_ARRAY_SIZE = 30000;
    public static int MAX_ARRAY_QTY = 10;

    String URI = "rmi://localhost:1903/sort";
    ServerActionsImpl serverActions;
    ArrayList<int[]> numbersToSort = new ArrayList<int[]>();

    public Server() {
        this.loadNumbersList();

        up();
    }

    public void up() {
        try {
            this.serverActions = new ServerActionsImpl(numbersToSort);

            Registry registry = LocateRegistry.createRegistry(1903);
            Naming.rebind(URI, this.serverActions);

            System.out.println("=======================");
            System.out.println("====== SERVER UP ======");
            System.out.println("=======================\n");

            this.showUnorderedList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] generateRandomNumbers() {
        Random rand = new Random();

        int numbers[] = new int[MAX_ARRAY_SIZE];

        for (int i =0; i < numbers.length; ++i) {
            numbers[i] = rand.nextInt(MAX_ARRAY_SIZE);
        }

        return numbers;
    }

    void loadNumbersList() {
        for (int i = 0; i < MAX_ARRAY_QTY; i++) {
            int[] numbers = generateRandomNumbers();

            numbersToSort.add(numbers);
        }
    }

    void showUnorderedList() {
        System.out.println("LISTA DE TAREFAS: \n");

        for (int[] numbers : numbersToSort) {
            int[] shortArray = new int[20];

            System.arraycopy(numbers, 0, shortArray, 0, 20);

            System.out.println(Arrays.toString(shortArray));
        }

        System.out.println("");
    }


    public static void main(String args[]) {
        Server server = new Server();
    }
}
