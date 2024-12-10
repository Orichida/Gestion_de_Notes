package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class Serveur {
    public static void main(String[] args) {
        note n1 = new note("Math", 15);
        note n2 = new note("Algo", 19);
        ArrayList<note> l1 = new ArrayList<>();
        l1.add(n1);
        l1.add(n2);

        note n3 = new note("Math", 18);
        note n4 = new note("Algo", 14);
        ArrayList<note> l2 = new ArrayList<>();
        l2.add(n3);
        l2.add(n4);

        etudiant e1 = new etudiant(1, "Ali", l1);
        etudiant e2 = new etudiant(2, "Nour", l2);
        ArrayList<etudiant> listEtud = new ArrayList<>();
        listEtud.add(e1);
        listEtud.add(e2);

        System.out.println("Server is starting ...");
        try {
            iGestion ecole = new Gestion(listEtud);
            LocateRegistry.createRegistry(2000);
            Naming.rebind("ecole", ecole);
            System.out.println("Server is running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
