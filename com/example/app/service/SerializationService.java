package com.example.app.service;

import com.example.app.model.Entreprise;
import java.io.*;

public class SerializationService {

    // Constantes pour les fichiers
    private static final String DATA_FOLDER = "data";
    private static final String FILE_ENTREPRISE = DATA_FOLDER + File.separator + "entreprise.ser";

    // Sauvegarde l'entreprise dans un fichier
    public static void saveEntreprise(Entreprise entreprise) {
        // Crée le dossier data si il n'existe pas
        new File(DATA_FOLDER).mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_ENTREPRISE))) {
            oos.writeObject(entreprise);
            System.out.println("Entreprise sauvegardée");
        } catch (Exception e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    // Charge l'entreprise depuis le fichier
    public static Entreprise loadEntreprise() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_ENTREPRISE))) {
            Entreprise entreprise = (Entreprise) ois.readObject();
            System.out.println("Entreprise chargée");
            return entreprise;
        } catch (FileNotFoundException e) {
            System.out.println("Pas de fichier entreprise, premier lancement");
            return null;
        } catch (Exception e) {
            System.out.println("Erreur chargement : " + e.getMessage());
            return null;
        }
    }
}