package com.example.app;

public class Main {

    public static void main(String[] args) {
        System.out.println("Application centrale démarrée");

        // Démarre le serveur TCP sur le port 9876
        SocketReceiver receiver = new SocketReceiver(9876);
        receiver.startListening();

        System.out.println("En attente de pointages...");

        // Garde le programme en vie indéfiniment
        // Sans ça le programme se terminerait immédiatement
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("Programme arrêté");
        }
    }
}