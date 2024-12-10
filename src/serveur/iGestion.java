package serveur;

import java.rmi.*;

public interface iGestion extends Remote {
    // methodes de enseignents
    public boolean ajoutNote(int id, String nom, int score) throws RemoteException;

    public boolean modifNote(int id, String nom, int socre) throws RemoteException;

    public boolean suppNote(int id, String nom) throws RemoteException;

    public boolean verifEns(String mdp) throws RemoteException;

    // methodes de etudiants
    public boolean verifEtud(int id, String mdp) throws RemoteException;

    public String affNote(int id) throws RemoteException;

    public boolean changMdp(int id, String oldMdp, String newMdp) throws RemoteException;
}
