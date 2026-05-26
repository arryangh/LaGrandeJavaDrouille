package com.example.app.service;

import com.example.app.model.Entreprise;
import com.example.pointingemulator.model.Employee;
import com.example.pointingemulator.model.Pointing;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.util.List;

public class SocketReceiver {

    private int port;
    private boolean running;
    private Entreprise entreprise;

    public SocketReceiver(int port, Entreprise entreprise) {
        this.port = port;
        this.running = false;
        this.entreprise = entreprise;
    }

    public void startListening() {
        running = true;
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Serveur en écoute sur le port " + port);

                while (running) {
                    Socket client = serverSocket.accept();
                    System.out.println("Connexion reçue de : " + client.getInetAddress());
                    new Thread(() -> handleClient(client)).start();
                }
            } catch (Exception e) {
                System.out.println("Erreur serveur : " + e.getMessage());
            }
        });

        serverThread.setDaemon(true);
        serverThread.start();
    }

    private void handleClient(Socket client) {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    client.getInputStream()
            );
            ObjectOutputStream oos = new ObjectOutputStream(
                    client.getOutputStream()
            );

            // Lit la requête
            Object request = ois.readObject();

            if (request instanceof String && request.equals("GET_EMPLOYEES")) {
                // Envoie la liste des employés
                List<Employee> employees = entreprise.getAllEmployees();
                oos.writeObject(employees);
                System.out.println("Liste employés envoyée : " + employees.size());

            } else if (request instanceof Pointing) {
                // Traite le pointage comme avant
                Pointing pointing = (Pointing) request;
                System.out.println("Pointage reçu : " + pointing);
                processPointing(pointing);
            }

            oos.close();
            ois.close();
            client.close();

        } catch (Exception e) {
            System.out.println("Erreur réception : " + e.getMessage());
        }
    }


    private void processPointing(Pointing pointing) {
        // Cherche l'employé par UUID dans l'entreprise
        Employee emp = entreprise.findEmployee(pointing.getEmployeeId());

        if (emp != null) {
            // Ajoute le pointage à l'employé
            emp.pointing();
            System.out.println("Pointage traité pour : " + emp.getName()
                    + " type : " + pointing.getType());
        } else {
            System.out.println("Employé introuvable : " + pointing.getEmployeeId());
        }
    }

    public void stop() {
        running = false;
    }
}