package client;

import java.rmi.*;
import serveur.iGestion;
import java.util.Scanner;

public class client {
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean state = true;
        try {
            cls();
            iGestion stub = (iGestion) Naming.lookup("ecole");
            Scanner sc = new Scanner(System.in);
            while (state) {
                System.out.println("1. Verifier etudiant");
                System.out.println("2. Verifier enseignant");
                System.out.println("3. Quitter");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        cls();
                        System.out.println("Donner ID: ");
                        int id = sc.nextInt();
                        cls();
                        System.out.println("Donner mot de passe de " + id + ": ");
                        String mdp = sc.next();
                        cls();
                        if (stub.verifEtud(id, mdp)) {
                            System.out.println("1. Afficher notes");
                            System.out.println("2. Changer mot de passe");
                            System.out.println("3. Quitter");
                            int choice2 = sc.nextInt();
                            cls();
                            switch (choice2) {
                                case 1:
                                    System.out.println(stub.affNote(id));
                                    System.out.println("Appuyer sur une touche pour continuer ...");
                                    sc.next();
                                    cls();
                                    break;
                                case 2:
                                    System.out.println("Donner ancien mot de passe: ");
                                    String oldMdp = sc.next();
                                    System.out.println("Donner nouveau mot de passe: ");
                                    String newMdp = sc.next();
                                    if (stub.changMdp(id, oldMdp, newMdp)) {
                                        System.out.println("Mot de passe changé avec succès");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    } else {
                                        System.out.println("Mot de passe changé échoué");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    }
                                    break;
                                case 3:
                                    state = false;
                                    break;
                                default:
                                    System.out.println("Choix invalide");
                                    System.out.println("Appuyer sur une touche pour continuer ...");
                                    sc.next();
                                    cls();
                            }
                        } else {
                            System.out.println("ID ou mot de passe incorrect");
                            System.out.println("Appuyer sur une touche pour continuer ...");
                            sc.next();
                            cls();
                        }
                        break;
                    case 2:
                        System.out.println("Donner mot de passe: ");
                        String mdpEns = sc.next();
                        cls();
                        if (stub.verifEns(mdpEns)) {
                            System.out.println("1. Ajouter note");
                            System.out.println("2. Modifier note");
                            System.out.println("3. Supprimer note");
                            System.out.println("4. Quitter");
                            int choice3 = sc.nextInt();
                            cls();
                            switch (choice3) {
                                case 1:
                                    System.out.println("Donner ID: ");
                                    int idEns = sc.nextInt();
                                    cls();
                                    System.out.println("Donner nom de matiere: ");
                                    String nom = sc.next();
                                    cls();
                                    System.out.println("Donner score: ");
                                    int score = sc.nextInt();
                                    cls();
                                    if (stub.ajoutNote(idEns, nom, score)) {
                                        System.out.println("Note ajoutée avec succès");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    } else {
                                        System.out.println("Note ajoutée échouée");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter id: ");
                                    int idEns2 = sc.nextInt();
                                    System.out.println(stub.affNote(idEns2));
                                    System.out.println("Enter nom: ");
                                    String nom2 = sc.next();
                                    System.out.println("Enter score: ");
                                    int score2 = sc.nextInt();
                                    if (stub.modifNote(idEns2, nom2, score2)) {
                                        System.out.println("Note modifiée avec succès");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    } else {
                                        System.out.println("Note modifiée échouée");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter id: ");
                                    int idEns3 = sc.nextInt();
                                    System.out.println(stub.affNote(idEns3));
                                    System.out.println("Enter nom: ");
                                    String nom3 = sc.next();
                                    if (stub.suppNote(idEns3, nom3)) {
                                        System.out.println("Note supprimée avec succès");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    } else {
                                        System.out.println("Note supprimée échouée");
                                        System.out.println("Appuyer sur une touche pour continuer ...");
                                        sc.next();
                                        cls();
                                    }
                                    break;
                                case 4:
                                    state = false;
                                    break;
                                default:
                                    System.out.println("Choix invalide");
                                    System.out.println("Appuyer sur une touche pour continuer ...");
                                    sc.next();
                                    cls();
                            }
                        } else {
                            System.out.println("Mot de passe incorrect");
                            System.out.println("Appuyer sur une touche pour continuer ...");
                            sc.next();
                            cls();
                        }
                        break;
                    case 3:
                        state = false;
                        break;
                    default:
                        System.out.println("Choix invalide");
                        System.out.println("Appuyer sur une touche pour continuer ...");
                        sc.next();
                        cls();
                }
                if (!state) {
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
