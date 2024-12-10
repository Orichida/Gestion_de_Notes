package serveur;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Gestion extends UnicastRemoteObject implements iGestion {
    private ArrayList<etudiant> etudiants;
    private String mdpEns = "admin";

    public Gestion(ArrayList<etudiant> etudiants) throws RemoteException {
        super();
        this.etudiants = new ArrayList<>(etudiants);
    }

    @Override
    public synchronized boolean verifEns(String mdp) throws RemoteException {
        if (mdpEns.equals(mdp)) {
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean verifEtud(int id, String mdp) throws RemoteException {
        for (etudiant etudiant : etudiants) {
            if (etudiant.getMdp().equals(mdp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean modifNote(int id, String nom, int score) throws RemoteException {
        for (etudiant e : etudiants) {
            if (e.getId() == id) {
                return e.setNote(nom, score);
            }
        }
        return false;
    }

    @Override
    public synchronized boolean ajoutNote(int id, String nom, int socre) throws RemoteException {
        note n = new note(nom, socre);
        for (etudiant e : etudiants) {
            if (e.getId() == id) {
                e.addNote(n);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean suppNote(int id, String nom) throws RemoteException {
        for (etudiant e : etudiants) {
            if (e.getId() == id) {
                return e.suppNote(nom);
            }
        }
        return false;

    }

    @Override
    public synchronized String affNote(int id) throws RemoteException {
        for (etudiant e : etudiants) {
            if (e.getId() == id) {
                return e.getNotes();
            }
        }
        return "-";
    }

    @Override
    public synchronized boolean changMdp(int id, String oldMdp, String newMdp) throws RemoteException {
        for (etudiant e : etudiants) {
            if (e.getId() == id && oldMdp.equals(e.getMdp())) {
                e.setPassword(newMdp);
                return true;
            }
        }
        return false;
    }

}
