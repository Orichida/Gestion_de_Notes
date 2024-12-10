package serveur;

import java.util.ArrayList;

public class etudiant {
    private int id;
    private String mdp;
    private ArrayList<note> notes;

    public etudiant(int id, String password, ArrayList<note> notes) {
        this.id = id;
        this.mdp = password;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setPassword(String password) {
        this.mdp = password;
    }

    public boolean setNote(String nom, int score) {
        for (note n : notes) {
            if (n.getNom().equals(nom)) {
                n.setScore(score);
                return true;
            }
        }
        return false;
    }

    public void addNote(note n) {
        this.notes.add(n);
    }

    public boolean suppNote(String nom) {
        return notes.removeIf(note -> note.getNom().equals(nom));
    }

    public String getNotes() {
        String res = "";
        for (note n : notes) {
            res += n.getNom() + "\t" + n.getScore() + "\n";
        }
        return res;
    }
}