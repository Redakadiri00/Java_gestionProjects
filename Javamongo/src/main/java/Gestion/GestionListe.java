package Gestion;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import Metiers.Fichiers;
import Metiers.Liste;
import Metiers.Tache;
import Persistence.ListDao;
import Persistence.TaskDao;

	public class GestionListe {
    ListDao ls;
    Liste le;
    TaskDao tk;

    public GestionListe() {
        this.ls = new ListDao();
        this.le = new Liste();
        this.tk=new TaskDao();
    }

    public List<Tache> readTasksListe(String nom) {
        return ls.getTasksByListName(nom);
    }

    public Liste readListeByTask(String nom) {
        return ls.getListByTaskName(nom);
    }

    public void modifierList(Liste l, String nom, String desc) {
        if (nom == null || nom.isEmpty() || desc == null || desc.isEmpty()) {
            showMessage("Le nom et la description ne doivent pas être vides.");
            return;
        }
        if (!isValidName(nom)) {
            showMessage("Le nom de la liste ne doit pas contenir de chiffres.");
            return;
        }
        ls.updateListe1(l, nom, desc);
    }

    public void supprimerFromListe(String lnom, String tnom) {
        ls.DeleteTaskFromProject(lnom, tnom);
    }

//    public void insererFile(String nomListe, String nomTask, Fichiers fiche) {
//        if (nomListe == null || nomListe.isEmpty() || nomTask == null || nomTask.isEmpty() || fiche == null) {
//            showMessage("Les détails du fichier ne doivent pas être vides.");
//            return;
//        }
//        ls.addFileToTask(nomListe, nomTask, fiche);
//    }

    public void creerListe(Liste l) {
        if (l.getNomListe() == null || l.getNomListe().isEmpty()) {
            showMessage("Le nom de la liste ne doit pas être vide.");
            return;
        }

        if (!isValidName(l.getNomListe())) {
            showMessage("Le nom de la liste ne doit pas contenir de chiffres.");
            return;
        }

        if (ls.isExistingList(l.getNomListe())) {
            showMessage("Une liste avec ce nom existe déjà.");
            return;
        }

        ls.createListe(l);
        
    }

    public void addTask(Liste l, Tache t) {
        if (l == null || t == null || t.getIntitule() == null || t.getIntitule().isEmpty() || 
            t.getDateDebutTache() == null || t.getDateDebutTache().isEmpty() || t.getDateFinTache() == null || t.getDateFinTache().isEmpty()) {
            showMessage("Tous les champs de la tâche sont obligatoires.");
            return;
        }

        if (!isValidName(t.getIntitule())) {
            showMessage("Le nom de la tâche ne doit pas contenir de chiffres.");
            return;
        }

//        if (!isValidDate(t.getDateDebutTache()) || !isValidDate(t.getDateFinTache())) {
//            showMessage("La date de début et la date de fin doivent avoir la forme jj/mm/yyyy.");
//            return;
//        }

        if (ls.isExistingList(l.getNomListe())) {
            if (tk.isExistingTache(t.getIntitule())) {
                showMessage("Une tâche avec ce nom existe déjà dans cette liste.");
                return;
            }
            ls.insertTaskToListt(l, t);
        } else {
            creerListe(l);
            ls.insertTaskToListt(l, t);
        }
        showMessage("Tâche ajoutée.");
    }
    
    public void addTask1(String nom, Tache t) {
        if (nom == null || nom.isEmpty() || t == null || t.getIntitule() == null || t.getIntitule().isEmpty() || 
            t.getDateDebutTache() == null || t.getDateDebutTache().isEmpty() || t.getDateFinTache() == null || t.getDateFinTache().isEmpty()) {
            showMessage("Tous les champs de la tâche sont obligatoires.");
            return;
        }
        
        if (!isValidName(t.getIntitule())) {
            showMessage("Le nom de la tâche ne doit pas contenir de chiffres.");
            return;
        }
        
        if (!isValidDate(t.getDateDebutTache()) || !isValidDate(t.getDateFinTache())) {
            showMessage("La date de début et la date de fin doivent avoir la forme jj/mm/yyyy.");
            return;
        }

        if (ls.isExistingList(nom)) {
            if (tk.isExistingTache( t.getIntitule())) {
                showMessage("Une tâche avec ce nom existe déjà dans cette liste.");
                return;
            }
            ls.insertTaskToListt1(nom, t);
        } else {
            creerListe(new Liste(nom, ""));
            ls.insertTaskToListt1(nom, t);
        }
        showMessage("Tâche ajoutée.");
    }
    
    public void deleteListe(String nom) {
        ls.deleteListName(nom);
    }
    
    public ArrayList<Liste> getAll() {
        return (ArrayList<Liste>) ls.getAllListe();
    }
    
    public ArrayList<Liste> recherche(String Key) {
        return (ArrayList<Liste>) ls.searchListByKeyword(Key);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private boolean isValidDate(String date) {
        if (date == null) return false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidName(String name) {
        if (name == null) return false;
        return !Pattern.compile("[0-9]").matcher(name).find();
    }

//    public List<Fichiers> getFiles(Tache tl) {
//        return tk.getFilesByTask(tl);
//    }

    public void modifyTaskList(String nomL, String nomT, String nvNom, String nvdes, String nvCat, String nvDaD, String nvDatF) {
        if (nvNom == null || nvNom.isEmpty() || nvdes == null || nvdes.isEmpty() || 
            nvCat == null || nvCat.isEmpty() || nvDaD == null || nvDaD.isEmpty() || nvDatF == null || nvDatF.isEmpty()) {
            showMessage("Tous les champs de la tâche sont obligatoires.");
            return;
        }

        if (!isValidDate(nvDaD) || !isValidDate(nvDatF)) {
            showMessage("La date de début et la date de fin doivent avoir la forme jj/mm/yyyy.");
            return;
        }
        if (!isValidName(nvNom)) {
            showMessage("Le nom de la tâche ne doit pas contenir de chiffres.");
            return;
        }
        ls.updateTacheListe(nomL, nomT, nvNom, nvCat, nvdes, nvDaD, nvDatF);
    }

    public Tache getTacheByNom(String nom) {
        this.tk = new TaskDao();
        return (tk.getTacheByName(nom));
    }

    

    public Liste listByName(String nom) {
        return ls.getListByName2(nom);
    }
}