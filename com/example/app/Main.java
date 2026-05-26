package com.example.app;

import com.example.app.model.Entreprise;
import com.example.app.service.SerializationService;
import com.example.app.service.SocketReceiver;

public class Main {

    public static void main(String[] args) {
        System.out.println("Application centrale démarrée");

        Entreprise entreprise = SerializationService.loadEntreprise();
        if (entreprise == null) {
            entreprise = createFakeEntreprise();
        }
        final Entreprise finalEntreprise = entreprise;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SerializationService.saveEntreprise(finalEntreprise);
        }));

        // Démarre le serveur TCP sur le port 9876
        SocketReceiver receiver = new SocketReceiver(9876, entreprise);
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

    // Données fictives F0
    private static Entreprise createFakeEntreprise() {
        Entreprise entreprise = Entreprise.getInstance("Polytech");
        entreprise.addDepartement("Informatique");
        entreprise.addDepartement("Marketing");
        entreprise.addEmployee("Jean Dupont", "Informatique");
        entreprise.addEmployee("Marie Martin", "Informatique");
        entreprise.addEmployee("Pierre Bernard", "Marketing");
        entreprise.addEmployee("Sophie Durand", "Marketing");
        entreprise.addEmployee("Aryan Ghalehnoee", "Informatique");
        System.out.println("Entreprise fictive créée");
        return entreprise;
    }
}