package server;

import server.ServerActions.ServerActionsImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server extends JFrame {
    String URI = "rmi://localhost:1903/sort";
    ServerActionsImpl serverActions;

    public Server() {
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

    public void initComponents() {
        this.sortButton = new JButton();
        this.sortButton.setText("Ordenar");
        this.sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    serverActions.send();
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
