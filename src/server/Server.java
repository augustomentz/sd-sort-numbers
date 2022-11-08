package server;

import server.ServerActions.ServerActionsImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

public class Server extends JFrame {
    public static int MAX_ARRAY_SIZE = 30000;
    public static int MAX_ARRAY_QTY = 15;

    String URI = "rmi://localhost:1903/sort";
    ServerActionsImpl serverActions;
    ArrayList<int[]> numbersToSort = new ArrayList<int[]>();

    public Server() {
        this.loadNumbersList();
        this.initComponents();
        this.setVisible(true);

        up();
    }

    public void up() {
        try {
            this.serverActions = new ServerActionsImpl();

            Registry registry = LocateRegistry.createRegistry(1903);
            Naming.rebind(URI, this.serverActions);

            System.out.println("=======================");
            System.out.println("====== SERVER UP ======");
            System.out.println("=======================");
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

    public void initComponents() {
        this.sortButton = new JButton();
        this.sortButton.setText("Ordenar");
        this.sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    serverActions.startProcessing(numbersToSort);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        this.setLayout(new GridBagLayout());
        this.setSize(300, 100);
        this.getContentPane().add(sortButton);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }

    private JButton sortButton;
}
