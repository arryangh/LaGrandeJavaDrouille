package com.example.app;

import com.example.pointingemulator.model.Pointing;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketReceiver {

    private int port;
    private boolean running;

    public SocketReceiver(int port) {
        this.port = port;
        this.running = false;
    }

    // Démarre le serveur TCP en arrière-plan
    public void startListening() {
        running = true;
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Serveur en écoute sur le port " + port);

                while (running) {
                    // Attend une connexion entrante
                    Socket client = serverSocket.accept();
                    System.out.println("Connexion reçue de : " + client.getInetAddress());

                    // Traite chaque connexion dans un thread séparé
                    // Comme ça plusieurs pointeuses peuvent envoyer en même temps
                    new Thread(() -> handleClient(client)).start();
                }
            } catch (Exception e) {
                System.out.println("Erreur serveur : " + e.getMessage());
            }
        });

        // Daemon = s'arrête quand l'appli se ferme
        serverThread.setDaemon(true);
        serverThread.start();
    }

    // Traite la connexion d'un client
    private void handleClient(Socket client) {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    client.getInputStream()
            );

            // Désérialise le pointage reçu
            Pointing pointing = (Pointing) ois.readObject();
            System.out.println("Pointage reçu : " + pointing);

            // Traitement du pointage - à compléter par leur code
            processPointing(pointing);

            ois.close();
            client.close();

        } catch (Exception e) {
            System.out.println("Erreur réception pointage : " + e.getMessage());
        }
    }

    // Traite le pointage reçu - à compléter selon leur logique
    private void processPointing(Pointing pointing) {
        // Ici ils cherchent l'employé par UUID et ajoutent le pointage
        // Exemple :
        // com.example.pointingemulator.model.Employee emp = entreprise.findEmployee(pointing.getEmployeeId());
        // emp.addPointing(pointing);
        System.out.println("Traitement du pointage pour l'employé : "
                + pointing.getEmployeeId()
                + " à " + pointing.getHour()
                + " type : " + pointing.getType());
    }

    public void stop() {
        running = false;
    }
}